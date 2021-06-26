package Utils;

import java.util.Random;

public class RandomUtil {
	public static int genererInt(int borneInf, int borneSup) {
		Random random = new Random();
		int nb;
		nb = borneInf + random.nextInt(borneSup - borneInf);
		return nb;
	}
}
