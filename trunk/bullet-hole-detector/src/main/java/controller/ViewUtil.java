package controller;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class ViewUtil {
	public static Border getSpacedEtchedBorder(int outsideSpace, int insideSpace) {
		return BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(outsideSpace, outsideSpace, outsideSpace, outsideSpace), BorderFactory.createEtchedBorder()),
				BorderFactory.createEmptyBorder(insideSpace, insideSpace, insideSpace, insideSpace));
	}
}
