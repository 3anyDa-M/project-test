package com.project.test.fileserv.service.reportService;

import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ExcelService {

    public ByteArrayOutputStream createExcel(Object obj) throws Exception {
        if (obj instanceof GetFullDataEmployeeResponse response) {
        return createExcelForEmployeeAndPasport(response);
        }
        throw new IllegalArgumentException("Неподдерживаемыйй тип : " + obj.getClass());
    }

    public ByteArrayOutputStream createExcelForEmployeeAndPasport(GetFullDataEmployeeResponse response) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Сотрудники
            writeListOnSheet(workbook, "Employees", response.employeeDTO());

            // Паспортные данные
            writeListOnSheet(workbook, "Passports", response.pasportDTO());

            workbook.write(outputStream);
            return outputStream;
        }
    }

    private void writeListOnSheet(XSSFWorkbook workbook, String sheetName, List<?> list) {
        if (list == null || list.isEmpty()) {
            log.warn("Список для листа {} пустой", sheetName);
            return;
        }

        XSSFSheet sheet = workbook.createSheet(sheetName);

        // Стили
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(boldFont(workbook));

        XSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("dd.mm.yyyy"));

        List<Map<String, Object>> flat = flattenCollection("", list);

        // Генерация заголовков (сохраняем порядок)
        LinkedHashSet<String> headersSet = new LinkedHashSet<>();
        for (Map<String, Object> map : flat) {
            headersSet.addAll(map.keySet());
        }
        List<String> headers = new ArrayList<>(headersSet);

        // Заголовок
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(formatHeader(headers.get(i)));
            cell.setCellStyle(headerStyle);
        }

        // Данные
        for (int rowIndex = 0; rowIndex < flat.size(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);
            Map<String, Object> record = flat.get(rowIndex);

            for (int colIndex = 0; colIndex < headers.size(); colIndex++) {
                Cell cell = row.createCell(colIndex);
                Object value = record.get(headers.get(colIndex));
                setCellValue(cell, value, dateStyle);
            }
        }

        // Форматирование листа
        sheet.createFreezePane(0, 1); // Заморозить заголовки
        for (int i = 0; i < Math.min(headers.size(), 50); i++) { // Лимит колонок
            sheet.autoSizeColumn(i);
        }
    }

    private XSSFFont boldFont(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        return font;
    }

    private String formatHeader(String header) {
        return Arrays.stream(header.split("\\."))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    private void setCellValue(Cell cell, Object value, XSSFCellStyle dateStyle) {
        if (value == null) {
            cell.setCellValue("");
            return;
        }

        if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof java.time.LocalDate) {
            cell.setCellValue((java.time.LocalDate) value);
            cell.setCellStyle(dateStyle);
        } else if (value instanceof java.util.Date) {
            cell.setCellValue((java.util.Date) value);
            cell.setCellStyle(dateStyle);
        } else {
            cell.setCellValue(value.toString());
        }
    }

    private List<Map<String, Object>> flattenCollection(String prefix, List<?> list) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object item : list) {
            Map<String, Object> map = new LinkedHashMap<>();
            flattenObjectFields(prefix, item, map);
            result.add(map);
        }
        return result;
    }

    private void flattenObjectFields(String prefix, Object obj, Map<String, Object> map) {
        if (obj == null) return;
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                map.put(prefix.isEmpty() ? field.getName() : prefix + "." + field.getName(), value);
            } catch (Exception ignore) {
            }
        }
    }


    // Рекурсивный метод для расплющивания вложенных структур
    private void flatten(String prefix, Object obj, Map<String, Object> map) {
        if (obj == null) return;

        if (obj instanceof Map) {
            Map<?, ?> mapObj = (Map<?, ?>) obj;
            for (Map.Entry<?, ?> entry : mapObj.entrySet()) {
                String key = entry.getKey().toString();
                flatten(prefix.isEmpty() ? key : prefix + "." + key, entry.getValue(), map);
            }
        } else if (obj instanceof List) {
            List<?> listObj = (List<?>) obj;
            for (int i = 0; i < listObj.size(); i++) {
                Object element = listObj.get(i);
                flatten(prefix + "[" + i + "]", element, map);
            }
        } else if (obj.getClass().getName().startsWith("com.project")) { // Для POJO, например DTO
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    flatten(prefix.isEmpty() ? field.getName() : prefix + "." + field.getName(), value, map);
                } catch (IllegalAccessException ignored) {
                }
            }
        } else {
            map.put(prefix, obj);
        }
    }

}
