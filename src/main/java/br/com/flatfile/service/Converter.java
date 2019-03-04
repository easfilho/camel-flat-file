package br.com.flatfile.service;

import br.com.flatfile.model.FlatFileData;

public interface Converter {
    FlatFileData convert(String data);
}
