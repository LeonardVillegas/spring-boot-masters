package com.api.estramipyme.DTOs;

import java.util.List;

public record DatabaseLoadDTO(
        long id,
        String section,
        String question,
        List<String> options
) {
}
