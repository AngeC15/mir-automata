package Model.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Model.automata.Automaton;
import Model.automata.ast.AST;
import Model.automata.creation.AstToObject;
import Model.automata.parser.AutomataParser;
import View.Template;

public class TemplatesLoader {
	
	private static TemplatesLoader instance = new TemplatesLoader();
	private HashMap<String, Template> templates;

	private TemplatesLoader() {
		templates = new HashMap<String, Template>();
	}

	public static void load(String templatename,String spritename, String automatname) throws IOException {
		instance.load_(templatename, spritename, automatname);
	}

	private void load_(String templatename,String spritename, String automatname) throws IOException {
			Template template = new Template(spritename, automatname);
			templates.put(templatename, template);
	}
	
	public static void load_all(String filename) throws IOException {
		instance.load_all_(filename);
	}
	
	private void load_all_(String filename) throws IOException {
		FileReader file = new FileReader(filename);
		BufferedReader br = new BufferedReader(file);
		String line;
		while((line = br.readLine()) != null) {
			String[] line_elems = line.split(";");
			for (int k = 0 ; k < line_elems.length ; k ++) {
				line_elems[k] = line_elems[k].strip();
				System.out.println("/"+line_elems[k]+"/");
			}
			load_(line_elems[0], line_elems[1], line_elems[2]);
		}
	}
	private Template get_(String name) {
		return templates.get(name);
	}
	public static Template get(String name){
		return instance.get_(name);
	}
	
}
