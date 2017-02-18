package com.sonie.web.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import resources.internal.Configuration;

public class FileUtil {

	public static void saveConfig(Configuration configuration) {
		Yaml yaml = new Yaml();
		Map<String, Object> data = new HashMap<>();
		try {
			data.put("hue", configuration.getHue());
			FileWriter writer = new FileWriter("./config/config.yml");
			yaml.dump(data, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
