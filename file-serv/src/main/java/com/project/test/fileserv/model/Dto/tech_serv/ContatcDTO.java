package com.project.test.fileserv.model.Dto.tech_serv;

import com.project.test.tech_serv.model.enums.ContatcType;
import lombok.Builder;

@Builder
public record ContatcDTO(long id,
                         ContatcType contatcType,
                         String value) {
}
