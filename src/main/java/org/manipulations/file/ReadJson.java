package org.manipulations.file;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.objects.car.Car;

public class ReadJson {

  public static ImmutableList<Car> getCarsList() throws IOException {

    String fileName = "carModels.json";

    ClassLoader cl = ReadJson.class.getClassLoader();

    InputStream input = cl.getResourceAsStream(fileName);

    assert input != null;
    String json = IOUtils.toString(input, Charset.defaultCharset());

    Type listType = new TypeToken<ArrayList<Car>>(){}.getType();

    List<Car> cars = new Gson().fromJson(json, listType);
    return ImmutableList.copyOf(cars);

  }

}
