package com.project.test.fileserv.model.Dto.tech_serv;

import com.project.test.fileserv.model.enums.ContatcType;
import lombok.Builder;

@Builder
public record ContatcDTO(long id,
                         ContatcType contatcType,
                         String value) {
}
