package Model.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import View.Season.EnumSeason;
import View.Template;

public class TemplatesLoader {

	private static TemplatesLoader instance = new TemplatesLoader();
	private HashMap<String, Template> templates;

	private TemplatesLoader() {
		templates = new HashMap<String, Template>();
	}

	public static void load(String templatename, String spritename, String automatname, int rows, int lines,
			int totalSprites) throws IOException {
		instance.load_(templatename, spritename, automatname, rows, lines, totalSprites);
	}

	private void load_(String templatename, String spritename, String automatname, int rows, int lines,
			int totalSprites) throws IOException {
		Template template = new Template(spritename, automatname, rows, lines, totalSprites);
		templates.put(templatename, template);
	}

	private void load_(EnumSeason season, String templatename, String spritename, String automatname, int rows,
			int lines, int totalSprites) throws IOException {
		Template template = new Template(season, spritename, automatname, rows, lines, totalSprites);
		templates.put(templatename, template);
	}

	public static void load_all(String filename) throws IOException {
		instance.load_all_(filename);
	}

	private void load_all_(String filename) throws IOException {
		FileReader file = new FileReader(filename);
		BufferedReader br = new BufferedReader(file);
		String line;
		while ((line = br.readLine()) != null) {
			String[] line_elems = line.split(";");
			for (int k = 0; k < line_elems.length; k++) {
				line_elems[k] = line_elems[k].strip();
				System.out.println("/" + line_elems[k] + "/");
			}
			String[] line_elems2 = line_elems[0].split("_");
			if (line_elems2.length == 2) { // Look if it is for a special season
				load_(EnumSeason.valueOf(line_elems2[1]), line_elems[0], line_elems[1], line_elems[2],
						Integer.parseInt(line_elems[3]), Integer.parseInt(line_elems[4]),
						Integer.parseInt(line_elems[5]));
			} else {
				load_(line_elems[0], line_elems[1], line_elems[2], Integer.parseInt(line_elems[3]),
						Integer.parseInt(line_elems[4]), Integer.parseInt(line_elems[5]));
			}
		}
	}

	private Template get_(String name) {
		return templates.get(name);
	}

	public static Template get(String name) {
		return instance.get_(name);
	}

	/**
	 * Retrieves the template associated with an entity for a given season. By default the sprite returned is the summer one
	 * @param name - name of the entity
	 * @param season - current season
	 * @return the associated template ( by default on summer)
	 */
	public static Template get(String name, EnumSeason season) {
		Template tpt;
		tpt = instance.get_(name + "_" + season);
		if (tpt == null) {
			tpt = instance.get_(name);
		}
		return tpt;
	}

}
