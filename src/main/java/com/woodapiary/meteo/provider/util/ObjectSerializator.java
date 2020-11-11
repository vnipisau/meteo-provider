/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectSerializator<T> {

    public T requestJsonFromUrl(String url, Class<T> clazz, Map<String, String> props) throws IOException {
        URLConnection connection;
        connection = new URL(url).openConnection();
        if (props != null) {
            props.entrySet().stream().forEach(e -> connection.setRequestProperty(e.getKey(), e.getValue()));
        }
        try (InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));) {
            final String response = rd.readLine();
            //System.out.println(response);
            final T owDto = new Gson().fromJson(response, clazz);
            //System.out.println(wsDto.toString());
            rd.close();
            return owDto;
        }
    }

    public T requestJsonFromUrl(String url, Class<T> clazz) throws IOException {
        return requestJsonFromUrl(url, clazz, null);
    }

    public T readJsonFromFile(final String path, Class<T> clazz) throws IOException {
        final FileInputStream fis = new FileInputStream(path);
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")))) {
            final Gson parser = new GsonBuilder()
                    //.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe())
                    .create();
            final T dto = parser.fromJson(rd, clazz);
            return dto;
        }
    }

    public List<T> readCsvFromFileV1(final File file, Class<T> clazz) throws IOException {
        try (final FileInputStream fis = new FileInputStream(file)) {
            final CsvMapper mapperCsv = new CsvMapper();
            final CsvSchema schema = mapperCsv.schemaFor(clazz).withHeader().withColumnReordering(true).withColumnSeparator('\t');
            final ObjectReader reader = mapperCsv.readerFor(clazz).with(schema);
            return reader.<T>readValues(fis).readAll();
        }
    }

    public List<T> readCsvFromFileV2(final InputStream stream, Class<T> clazz) throws IOException {
        final CsvMapper mapperCsv = new CsvMapper();
        final CsvSchema schema = mapperCsv.schemaFor(clazz).withHeader().withColumnReordering(true).withColumnSeparator('\t');
        final ObjectReader reader = mapperCsv.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }

}
