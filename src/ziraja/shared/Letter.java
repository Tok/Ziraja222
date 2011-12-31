package ziraja.shared;

/**
 * 1 2 3 4 5 6 7 8 9
 * A B C D E F G H I
 * J K L M N O P Q R
 * S T U V W X Y Z
 */
public enum Letter {
	A(1, 1, 1, "AJS"), 
	B(2, 2, 2, "BKT"), 
	C(3, 3, 3, "CLU"), 
	D(4, 4, 4, "DMV"), 
	E(5, 5, 5, "ENW"), 
	F(6, 6, 6, "FOX"), 
	G(7, 7, 7, "GPY"), 
	H(8, 8, 8, "HQZ"), 
	I(9, 9, 9, "IR"), 
	J(10, 10, 1, "AJS"), 
	K(11, 20, 2, "BKT"),  
	L(12, 30, 3, "CLU"), 
	M(13, 40, 4, "DMV"), 
	N(14, 50, 5, "ENW"), 
	O(15, 60, 6, "FOX"), 
	P(16, 70, 7, "GPY"), 
	Q(17, 80, 8, "HQZ"), 
	R(18, 90, 9, "IR"), 
	S(19, 100, 1, "AJS"), 
	T(20, 200, 2, "BKT"), 
	U(21, 300, 3, "CLU"), 
	V(22, 400, 4, "DMV"), 
	W(23, 500, 5, "ENW"), 
	X(24, 600, 6, "FOX"), 
	Y(25, 700, 7, "GPY"), 
	Z(26, 800, 8, "HQZ");

	public int number; //small range table
	public int full; //large range table
	public int reduced; //pythagorean table
	public String group; 
	
	private Letter(int number, int full, int reduced, String group) {
		this.number = number;
		this.full = full;
		this.reduced = reduced;
		this.group = group;
	}
}
