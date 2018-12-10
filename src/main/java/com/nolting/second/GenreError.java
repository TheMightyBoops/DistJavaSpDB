package com.nolting.second;

import org.springframework.boot.web.servlet.error.ErrorController;

public class GenreError implements ErrorController {
    @Override
    public String getErrorPath() {
        return null;
    }
}
