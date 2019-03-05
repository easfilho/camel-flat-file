package br.com.flatfile.service;

import br.com.flatfile.model.FlatFileData;

public interface FlatFileConverter {
    FlatFileData convert(String data);
}
