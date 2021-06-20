package Tests;

import java.io.IOException;

import Model.automata.Automaton;
import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import View.Template;

public class Test_TemplateLoader {

	public static void loader(String filename) throws IOException {
		TemplatesLoader.load_all(filename);
		Template template = TemplatesLoader.get("test");
		//template.displayAllNodes();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Test Start");
		loader("src/Tests/templateloader_resources.txt");
		System.out.println("Test End");
	}
	
}
