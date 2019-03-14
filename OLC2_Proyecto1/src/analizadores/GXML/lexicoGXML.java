/* descargar la ultima version como main.java -> https://www.cs.princeton.edu/~appel/modern/java/JLex/ */
/*------------------- 1era Area: Codigo de usuario ------------------------*/
package analizadores.GXML;
import java_cup.runtime.Symbol;
/*----------------- 2da Area: Opciones y declaraciones --------------------*/


public class lexicoGXML       implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

//-------------------------------> Codigo de usuario en sintaxis java 
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public lexicoGXML       (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public lexicoGXML       (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private lexicoGXML       () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

    yyline = 1;     
    yychar = 1;     
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int MAYORQUE = 2;
	private final int COMENT_MULTI_LINE = 1;
	private final int yy_state_dtrans[] = {
		0,
		66,
		70
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NO_ANCHOR,
		/* 199 */ YY_NO_ANCHOR,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NO_ANCHOR,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NO_ANCHOR,
		/* 206 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"30:9,36,33,30,36,34,30:18,36,30,29,35,37,30:2,29,30:6,28,24,27:10,30:2,26,2" +
"5,30:3,5,7,13,14,1,18,23,11,4,31:2,16,12,2,8,10,31,6,19,9,22,3,31,15,17,31," +
"30:4,32,30,5,7,13,14,1,18,23,11,4,31:2,16,12,2,8,10,31,6,19,9,22,3,31,15,17" +
",31,20,30,21,30:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,207,
"0,1,2,3:2,4,1,4:2,1,5,4,6,7,3,7,3,1:2,3:12,8,3:15,1,9,1,10,11,1:2,12,1,13,1" +
"4,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,3" +
"9,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,21,58,59,60,61,62,6" +
"3,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,8" +
"8,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109," +
"110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128" +
",129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,14" +
"7,148,149,150,151,152,153,154,155,156,157,158,159,3,160,161")[0];

	private int yy_nxt[][] = unpackFromString(162,38,
"1,2,198,202,57,132,203,158,204,100,133,204,205,159,134,3,206,4,160,204,5,6," +
"204:2,7,8,9,10,11,12,11,204,11,13,58,102,58,11,-1:39,204,161,204:17,11,-1,2" +
"04:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:19,11,-1,204:2,11:2,-1,162" +
",11,-1,11,204,162,-1,11:4,-1,11:20,-1,11:4,-1,11:2,-1,11:3,-1,11:4,-1,11:20" +
",-1,11:4,-1,10,64,-1,11:3,-1,11:4,-1,56:28,-1,56:3,-1,56:4,-1:33,15:2,-1,15" +
",-1:2,204:3,194,204:13,195,204,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1," +
"11:4,-1:35,49,-1:3,50:19,51,-1,50:2,51:2,-1,50,51,-1,51,50:2,-1,51:4,-1,51:" +
"20,-1,51:4,-1,51:2,-1,51:3,-1,51:4,-1:33,54:2,-1,54,-1:2,56:28,17,56:3,-1,5" +
"6:4,-1,204:13,14,204:5,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1," +
"11:20,-1,11:4,-1,11:2,-1,11:3,15,58,11,58,11,-1:33,18,-1:5,51:20,-1,51:4,-1" +
",51:2,-1,51:3,54,60,51,60,51,-1:33,55,-1:5,62:32,18,59,62:3,-1,204:11,16,20" +
"4:7,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,11:20,-1,11:4,-1,10" +
"1,11,-1,11:3,-1,11:4,-1,65:20,73,65:4,73,65:2,73,65:3,55,69,65:3,1,47:34,-1" +
",47,48,-1,204:7,19,204:11,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4," +
"-1,68:20,62,68:4,62,68:2,62,68:3,18,72,68:3,-1,51:20,-1,51:4,-1,51:2,-1,51:" +
"3,55,51:4,1,50:19,51,52,50:2,51:2,53,51:2,52,51,50,51,54,60,103,60,51,-1,20" +
"4:7,20,204:11,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,11:20,-1," +
"11:4,-1,11:2,-1,11:3,18,11:4,-1,73:32,55,61,73:3,-1,204:10,21,204:8,11,-1,2" +
"04:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7,22,204:11,11,-1,204:2,11" +
":2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7,23,204:11,11,-1,204:2,11:2,-1,1" +
"62,11,-1,11,204,162,-1,11:4,-1,24,204:18,11,-1,204:2,11:2,-1,162,11,-1,11,2" +
"04,162,-1,11:4,-1,204,25,204:17,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1" +
",11:4,-1,204:7,26,204:11,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-" +
"1,204:5,27,204:13,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7" +
",28,204:11,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:5,29,204" +
":13,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,30,204:18,11,-1,204" +
":2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204,31,204:17,11,-1,204:2,11:2,-" +
"1,162,11,-1,11,204,162,-1,11:4,-1,204:7,32,204:11,11,-1,204:2,11:2,-1,162,1" +
"1,-1,11,204,162,-1,11:4,-1,204:7,33,204:11,11,-1,204:2,11:2,-1,162,11,-1,11" +
",204,162,-1,11:4,-1,34,204:18,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,1" +
"1:4,-1,204:4,35,204:14,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1," +
"204:4,36,204:14,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:15," +
"37,204:3,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:4,38,204:1" +
"4,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7,39,204:11,11,-1" +
",204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7,40,204:11,11,-1,204:2," +
"11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:4,41,204:14,11,-1,204:2,11:2,-1" +
",162,11,-1,11,204,162,-1,11:4,-1,204:4,42,204:14,11,-1,204:2,11:2,-1,162,11" +
",-1,11,204,162,-1,11:4,-1,204:5,43,204:13,11,-1,204:2,11:2,-1,162,11,-1,11," +
"204,162,-1,11:4,-1,204:18,44,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11" +
":4,-1,204:15,45,204:3,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,2" +
"04:15,46,204:3,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,137,204:" +
"2,105,63,204:14,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,11:20,-" +
"1,11:4,-1,11:2,-1,11:3,-1,11,68,11:2,-1,51:20,-1,51:4,-1,51:2,-1,51:3,-1,51" +
",65,51:2,-1,204:8,67,204:10,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:" +
"4,-1,204:9,71,204:9,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204" +
":8,74,204:10,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:8,75,2" +
"04:10,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:10,76,204:8,1" +
"1,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:13,77,204:5,11,-1,20" +
"4:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7,78,204:11,11,-1,204:2,11:" +
"2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:8,79,204:10,11,-1,204:2,11:2,-1,16" +
"2,11,-1,11,204,162,-1,11:4,-1,204:7,80,204:11,11,-1,204:2,11:2,-1,162,11,-1" +
",11,204,162,-1,11:4,-1,204:18,81,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-" +
"1,11:4,-1,204:4,82,204:14,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4," +
"-1,204:5,83,204:13,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:" +
"7,84,204:11,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:11,85,2" +
"04:7,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:11,86,204:7,11" +
",-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:8,87,204:10,11,-1,204" +
":2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:8,88,204:10,11,-1,204:2,11:2" +
",-1,162,11,-1,11,204,162,-1,11:4,-1,204,89,204:17,11,-1,204:2,11:2,-1,162,1" +
"1,-1,11,204,162,-1,11:4,-1,204:7,90,204:11,11,-1,204:2,11:2,-1,162,11,-1,11" +
",204,162,-1,11:4,-1,204:2,91,204:16,11,-1,204:2,11:2,-1,162,11,-1,11,204,16" +
"2,-1,11:4,-1,204:8,92,204:10,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11" +
":4,-1,204:5,93,204:13,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,2" +
"04:3,94,204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:3,95" +
",204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7,96,204:11" +
",11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7,97,204:11,11,-1," +
"204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:4,98,204:14,11,-1,204:2,1" +
"1:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:4,99,204:14,11,-1,204:2,11:2,-1," +
"162,11,-1,11,204,162,-1,11:4,-1,204,135,204:10,166,204:2,104,204:3,11,-1,20" +
"4:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:4,106,204:14,11,-1,204:2,11" +
":2,-1,162,11,-1,11,204,162,-1,11:4,-1,199,204:3,107,204:14,11,-1,204:2,11:2" +
",-1,162,11,-1,11,204,162,-1,11:4,-1,204:12,108,204:6,11,-1,204:2,11:2,-1,16" +
"2,11,-1,11,204,162,-1,11:4,-1,204:5,109,204:2,110,204:10,11,-1,204:2,11:2,-" +
"1,162,11,-1,11,204,162,-1,11:4,-1,204:14,111,204:4,11,-1,204:2,11:2,-1,162," +
"11,-1,11,204,162,-1,11:4,-1,204,179,204:13,112,204:3,11,-1,204:2,11:2,-1,16" +
"2,11,-1,11,204,162,-1,11:4,-1,204:15,113,204:3,11,-1,204:2,11:2,-1,162,11,-" +
"1,11,204,162,-1,11:4,-1,204:3,114,204:15,11,-1,204:2,11:2,-1,162,11,-1,11,2" +
"04,162,-1,11:4,-1,204:6,115,204:12,11,-1,204:2,11:2,-1,162,11,-1,11,204,162" +
",-1,11:4,-1,204:3,116,204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11" +
":4,-1,204:3,117,204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1," +
"204:3,118,204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204,11" +
"9,204:17,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:3,120,204:" +
"15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:4,121,204:14,11," +
"-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,188,204:4,122,204:13,11,-1" +
",204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:3,123,204:15,11,-1,204:2" +
",11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:12,124,204:6,11,-1,204:2,11:2," +
"-1,162,11,-1,11,204,162,-1,11:4,-1,125,204:18,11,-1,204:2,11:2,-1,162,11,-1" +
",11,204,162,-1,11:4,-1,204:12,126,204:6,11,-1,204:2,11:2,-1,162,11,-1,11,20" +
"4,162,-1,11:4,-1,204:13,127,204:5,11,-1,204:2,11:2,-1,162,11,-1,11,204,162," +
"-1,11:4,-1,204:13,128,204:5,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:" +
"4,-1,204:8,129,204:10,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,2" +
"04,130,204:17,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:3,131" +
",204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7,136,204:1" +
"1,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:7,138,204:11,11,-" +
"1,171,204,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:4,139,204:14,11,-1,17" +
"3,204,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:2,140,204:16,11,-1,204:2," +
"11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,162:19,11,-1,162:2,11:2,-1,162,11,-" +
"1,11,162:2,-1,11:4,-1,204:19,11,-1,204,174,11:2,-1,162,11,-1,11,204,162,-1," +
"11:4,-1,204:11,141,204:7,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-" +
"1,204,175,204:3,176,204:13,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4" +
",-1,204:12,142,204:6,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,20" +
"4:17,177,204,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204,143,20" +
"4:17,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:14,144,204:4,1" +
"1,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:15,178,204:3,11,-1,2" +
"04:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:5,180,204:13,11,-1,204:2,1" +
"1:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:18,200,11,-1,204:2,11:2,-1,162,1" +
"1,-1,11,204,162,-1,11:4,-1,145,204:18,11,-1,204:2,11:2,-1,162,11,-1,11,204," +
"162,-1,11:4,-1,204:5,146,204:13,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1" +
",11:4,-1,204:8,147,204:10,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4," +
"-1,204:13,182,204:5,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,183" +
",204:18,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:8,184,204:1" +
"0,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:8,148,204:10,11,-" +
"1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:18,149,11,-1,204:2,11:2" +
",-1,162,11,-1,11,204,162,-1,11:4,-1,150,204:18,11,-1,204:2,11:2,-1,162,11,-" +
"1,11,204,162,-1,11:4,-1,204:4,185,204:14,11,-1,204:2,11:2,-1,162,11,-1,11,2" +
"04,162,-1,11:4,-1,204:5,186,204:13,11,-1,204:2,11:2,-1,162,11,-1,11,204,162" +
",-1,11:4,-1,204:3,187,204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11" +
":4,-1,204:13,151,204:5,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1," +
"190,204:18,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:11,191,2" +
"04:7,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204,192,204:17,11," +
"-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:13,193,204:5,11,-1,204" +
":2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204,152,204:17,11,-1,204:2,11:2," +
"-1,162,11,-1,11,204,162,-1,11:4,-1,153,204:18,11,-1,204:2,11:2,-1,162,11,-1" +
",11,204,162,-1,11:4,-1,154,204:18,11,-1,204:2,11:2,-1,162,11,-1,11,204,162," +
"-1,11:4,-1,204:4,155,204:14,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:" +
"4,-1,204,196,204:17,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204" +
":3,156,204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:3,197" +
",204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:12,157,204:" +
"6,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,163,204:6,164,204:11," +
"11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:17,181,204,11,-1,20" +
"4:2,11:2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:8,201,204:10,11,-1,204:2,11" +
":2,-1,162,11,-1,11,204,162,-1,11:4,-1,204:4,189,204:14,11,-1,204:2,11:2,-1," +
"162,11,-1,11,204,162,-1,11:4,-1,165,204:18,11,-1,204:2,11:2,-1,162,11,-1,11" +
",204,162,-1,11:4,-1,167,204:18,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1," +
"11:4,-1,204:3,168,169,204:14,11,-1,170,204,11:2,-1,162,11,-1,11,204,162,-1," +
"11:4,-1,204:3,172,204:15,11,-1,204:2,11:2,-1,162,11,-1,11,204,162,-1,11:4");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -3:
						break;
					case 3:
						{ return new Symbol(sym.res_x,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{ return new Symbol(sym.res_y,yyline,yychar, yytext());}
					case -5:
						break;
					case 5:
						{ return new Symbol(sym.res_llvOpen,yyline,yychar, yytext());}
					case -6:
						break;
					case 6:
						{ return new Symbol(sym.res_llvClose,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{ return new Symbol(sym.res_slash,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.res_Igual,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.res_MenorQue,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{ return new Symbol(sym.tNumerico,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
				}
					case -13:
						break;
					case 13:
						{yychar=1;}
					case -14:
						break;
					case 14:
						{ return new Symbol(sym.res_id,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{}
					case -16:
						break;
					case 16:
						{ return new Symbol(sym.res_tam,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.tCad,yyline,yychar, (yytext()).substring(1,yytext().length()-1));}
					case -18:
						break;
					case 18:
						{yychar=1;}
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.res_alto,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.res_tipo,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.res_path,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.res_dato,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{ return new Symbol(sym.res_ancho,yyline,yychar, yytext());}
					case -24:
						break;
					case 24:
						{ return new Symbol(sym.res_borde,yyline,yychar, yytext());}
					case -25:
						break;
					case 25:
						{ return new Symbol(sym.res_boton,yyline,yychar, yytext());}
					case -26:
						break;
					case 26:
						{ return new Symbol(sym.res_texto,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.res_color,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{ return new Symbol(sym.res_falso,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.res_enviar,yyline,yychar, yytext());}
					case -30:
						break;
					case 30:
						{ return new Symbol(sym.res_nombre,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.res_accion,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.res_minimo,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.res_maximo,yyline,yychar, yytext());}
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.res_fuente,yyline,yychar, yytext());}
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.res_negrita,yyline,yychar, yytext());}
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.res_ventana,yyline,yychar, yytext());}
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.res_control,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.res_cursiva,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.res_defecto,yyline,yychar, yytext());}
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.res_verdadero,yyline,yychar, yytext());}
					case -41:
						break;
					case 41:
						{ return new Symbol(sym.res_referencia,yyline,yychar, yytext());}
					case -42:
						break;
					case 42:
						{ return new Symbol(sym.res_multimedia,yyline,yychar, yytext());}
					case -43:
						break;
					case 43:
						{ return new Symbol(sym.res_contenedor,yyline,yychar, yytext());}
					case -44:
						break;
					case 44:
						{ return new Symbol(sym.res_listaDatos,yyline,yychar, yytext());}
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.res_accionFinal,yyline,yychar, yytext());}
					case -46:
						break;
					case 46:
						{ return new Symbol(sym.res_accionInicial,yyline,yychar, yytext());}
					case -47:
						break;
					case 47:
						{}
					case -48:
						break;
					case 48:
						{}
					case -49:
						break;
					case 49:
						{yybegin(YYINITIAL);}
					case -50:
						break;
					case 50:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -51:
						break;
					case 51:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -52:
						break;
					case 52:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
				}
					case -53:
						break;
					case 53:
						{yybegin(YYINITIAL); return new Symbol(sym.res_MenorQue,yyline,yychar, yytext());}
					case -54:
						break;
					case 54:
						{}
					case -55:
						break;
					case 55:
						{yychar=1;}
					case -56:
						break;
					case 57:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -57:
						break;
					case 58:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -58:
						break;
					case 59:
						{yychar=1;}
					case -59:
						break;
					case 60:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -60:
						break;
					case 61:
						{yychar=1;}
					case -61:
						break;
					case 63:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -62:
						break;
					case 64:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -63:
						break;
					case 65:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -64:
						break;
					case 67:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -65:
						break;
					case 68:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -66:
						break;
					case 69:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -67:
						break;
					case 71:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -68:
						break;
					case 72:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -69:
						break;
					case 74:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -70:
						break;
					case 75:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -71:
						break;
					case 76:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -72:
						break;
					case 77:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -73:
						break;
					case 78:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -74:
						break;
					case 79:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -75:
						break;
					case 80:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -76:
						break;
					case 81:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -77:
						break;
					case 82:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -78:
						break;
					case 83:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -79:
						break;
					case 84:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -80:
						break;
					case 85:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -81:
						break;
					case 86:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -82:
						break;
					case 87:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -83:
						break;
					case 88:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -84:
						break;
					case 89:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -85:
						break;
					case 90:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -86:
						break;
					case 91:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -87:
						break;
					case 92:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -88:
						break;
					case 93:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -89:
						break;
					case 94:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -90:
						break;
					case 95:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -91:
						break;
					case 96:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -92:
						break;
					case 97:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -93:
						break;
					case 98:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -94:
						break;
					case 99:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -95:
						break;
					case 100:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -96:
						break;
					case 101:
						{ return new Symbol(sym.tNumerico,yyline,yychar, yytext());}
					case -97:
						break;
					case 102:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -98:
						break;
					case 103:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -99:
						break;
					case 104:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -100:
						break;
					case 105:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -101:
						break;
					case 106:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -102:
						break;
					case 107:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -103:
						break;
					case 108:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -104:
						break;
					case 109:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -105:
						break;
					case 110:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -106:
						break;
					case 111:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -107:
						break;
					case 112:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -108:
						break;
					case 113:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -109:
						break;
					case 114:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -110:
						break;
					case 115:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -111:
						break;
					case 116:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -112:
						break;
					case 117:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -113:
						break;
					case 118:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -114:
						break;
					case 119:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -115:
						break;
					case 120:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -116:
						break;
					case 121:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -117:
						break;
					case 122:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -118:
						break;
					case 123:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -119:
						break;
					case 124:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -120:
						break;
					case 125:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -121:
						break;
					case 126:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -122:
						break;
					case 127:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -123:
						break;
					case 128:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -124:
						break;
					case 129:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -125:
						break;
					case 130:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -126:
						break;
					case 131:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -127:
						break;
					case 132:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -128:
						break;
					case 133:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -129:
						break;
					case 134:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -130:
						break;
					case 135:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -131:
						break;
					case 136:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -132:
						break;
					case 137:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -133:
						break;
					case 138:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -134:
						break;
					case 139:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -135:
						break;
					case 140:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -136:
						break;
					case 141:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -137:
						break;
					case 142:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -138:
						break;
					case 143:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -139:
						break;
					case 144:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -140:
						break;
					case 145:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -141:
						break;
					case 146:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -142:
						break;
					case 147:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -143:
						break;
					case 148:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -144:
						break;
					case 149:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -145:
						break;
					case 150:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -146:
						break;
					case 151:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -147:
						break;
					case 152:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -148:
						break;
					case 153:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -149:
						break;
					case 154:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -150:
						break;
					case 155:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -151:
						break;
					case 156:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -152:
						break;
					case 157:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -153:
						break;
					case 158:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -154:
						break;
					case 159:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -155:
						break;
					case 160:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -156:
						break;
					case 161:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -157:
						break;
					case 162:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -158:
						break;
					case 163:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -159:
						break;
					case 164:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -160:
						break;
					case 165:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -161:
						break;
					case 166:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -162:
						break;
					case 167:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -163:
						break;
					case 168:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -164:
						break;
					case 169:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -165:
						break;
					case 170:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -166:
						break;
					case 171:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -167:
						break;
					case 172:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -168:
						break;
					case 173:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -169:
						break;
					case 174:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -170:
						break;
					case 175:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -171:
						break;
					case 176:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -172:
						break;
					case 177:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -173:
						break;
					case 178:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -174:
						break;
					case 179:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -175:
						break;
					case 180:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -176:
						break;
					case 181:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -177:
						break;
					case 182:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -178:
						break;
					case 183:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -179:
						break;
					case 184:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -180:
						break;
					case 185:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -181:
						break;
					case 186:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -182:
						break;
					case 187:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -183:
						break;
					case 188:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -184:
						break;
					case 189:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -185:
						break;
					case 190:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -186:
						break;
					case 191:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -187:
						break;
					case 192:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -188:
						break;
					case 193:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -189:
						break;
					case 194:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -190:
						break;
					case 195:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -191:
						break;
					case 196:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -192:
						break;
					case 197:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -193:
						break;
					case 198:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -194:
						break;
					case 199:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -195:
						break;
					case 200:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -196:
						break;
					case 201:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -197:
						break;
					case 202:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -198:
						break;
					case 203:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -199:
						break;
					case 204:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -200:
						break;
					case 205:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -201:
						break;
					case 206:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -202:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
