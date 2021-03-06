package Tests;

import java.io.IOException;

import Model.loader.TemplatesLoader;
import View.Template;

public class Test_TemplateLoader {

	public static void loader(String filename) throws NumberFormatException, Exception {
		TemplatesLoader.load_all(filename);
		Template template = TemplatesLoader.get("test");
	}

	public static void main(String[] args) throws NumberFormatException, Exception {
		System.out.println("Test Start");
		loader("src/Tests/templateloader_resources.txt");
		System.out.println("Test End");
	}

}
