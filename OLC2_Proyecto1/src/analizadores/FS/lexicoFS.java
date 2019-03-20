/* descargar la ultima version como main.java -> https://www.cs.princeton.edu/~appel/modern/java/JLex/ */
/*------------------- 1era Area: Codigo de usuario ------------------------*/
package analizadores.FS;
import java_cup.runtime.Symbol;
import FuncionScript.ErroresFS.ManejadorErroresFS;
/*----------------- 2da Area: Opciones y declaraciones --------------------*/


public class lexicoFS       implements java_cup.runtime.Scanner {
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

	public lexicoFS       (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public lexicoFS       (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private lexicoFS       () {
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
	private final int COMENT_MULTI_LINE = 1;
	private final int yy_state_dtrans[] = {
		0,
		81
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
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
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
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NO_ANCHOR,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NO_ANCHOR,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NO_ANCHOR,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NO_ANCHOR,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NO_ANCHOR,
		/* 218 */ YY_NO_ANCHOR,
		/* 219 */ YY_NO_ANCHOR,
		/* 220 */ YY_NO_ANCHOR,
		/* 221 */ YY_NO_ANCHOR,
		/* 222 */ YY_NO_ANCHOR,
		/* 223 */ YY_NO_ANCHOR,
		/* 224 */ YY_NO_ANCHOR,
		/* 225 */ YY_NO_ANCHOR,
		/* 226 */ YY_NO_ANCHOR,
		/* 227 */ YY_NO_ANCHOR,
		/* 228 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"46:9,50,48,46,50,49,46:18,50,41,45,46:3,42,45,35,36,33,30,29,31,26,32,44:10" +
",25,28,40,27,39,24,46,1,7,3,18,6,23,16,47,4,11,47,2,17,10,8,13,14,5,20,9,15" +
",19,47,12,47:2,21,46,22,34,47,46,1,7,3,18,6,23,16,47,4,11,47,2,17,10,8,13,1" +
"4,5,20,9,15,19,47,12,47:2,37,43,38,46:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,229,
"0,1,2,1:5,3,1:2,4,5,6,7,1:5,8,9,10,11,12,13:2,14,1:14,15,1,16,15:23,1,17,1," +
"18,19,20,21,16,22,23:2,24,1,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40" +
",41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65" +
",66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90" +
",91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111" +
",112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,13" +
"0,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,1" +
"49,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167," +
"168,15,169,170,171")[0];

	private int yy_nxt[][] = unpackFromString(172,51,
"1,2,210,127,220,224,225:2,226,225:8,227,228,104,72,3,4,149,5,6,7,8,9,10,11," +
"12,13,14,15,16,17,18,19,20,21,22,23,73,24,77,80,225,25,26:2,-1:52,225,150,2" +
"25:17,151,-1:2,225,-1:20,152,-1:2,225,-1:30,28,-1:50,29,-1:2,30,-1:47,31,-1" +
":3,32,-1:46,33,-1:4,71,34,-1:44,35,-1:50,36,-1:50,37,-1:50,38,-1:65,39,-1:3" +
"4,75,-1:17,24,-1:54,26:3,-1,225:9,82,225:10,-1:2,225,-1:20,152,-1:2,225,-1:" +
"4,225:20,-1:2,225,-1:20,152,-1:2,225,-1:47,44,-1:38,70,-1:19,71:47,43,74,71" +
",-1,225:3,27,225,161,225:14,-1:2,225,-1:20,152,-1:2,225,-1:46,40,-1:55,43,-" +
"1:3,225:4,42,225:15,-1:2,225,-1:20,152,-1:2,225,-1:4,78:44,41,78:2,-1,78:2," +
"-1,225:7,45,225:12,-1:2,225,-1:20,152,-1:2,225,-1:3,1,68:31,-1,69,68:17,-1," +
"225:7,46,225:12,-1:2,225,-1:20,152,-1:2,225,-1:4,225:7,47,225:12,-1:2,225,-" +
"1:20,152,-1:2,225,-1:4,225:2,48,225:17,-1:2,225,-1:20,152,-1:2,225,-1:4,225" +
":7,49,225:12,-1:2,225,-1:20,152,-1:2,225,-1:4,225:7,50,225:12,-1:2,225,-1:2" +
"0,152,-1:2,225,-1:4,225:4,51,225:15,-1:2,225,-1:20,152,-1:2,225,-1:4,225:7," +
"52,225:12,-1:2,225,-1:20,152,-1:2,225,-1:4,225:9,53,225:10,-1:2,225,-1:20,1" +
"52,-1:2,225,-1:4,225,54,225:18,-1:2,225,-1:20,152,-1:2,225,-1:4,225:4,55,22" +
"5:15,-1:2,225,-1:20,152,-1:2,225,-1:4,225:4,56,225:15,-1:2,225,-1:20,152,-1" +
":2,225,-1:4,225:4,57,225:15,-1:2,225,-1:20,152,-1:2,225,-1:4,225:7,58,225:1" +
"2,-1:2,225,-1:20,152,-1:2,225,-1:4,225:5,59,225:14,-1:2,225,-1:20,152,-1:2," +
"225,-1:4,225:9,60,225:10,-1:2,225,-1:20,152,-1:2,225,-1:4,225:7,61,225:12,-" +
"1:2,225,-1:20,152,-1:2,225,-1:4,62,225:19,-1:2,225,-1:20,152,-1:2,225,-1:4," +
"225:5,63,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,64,225:19,-1:2,225,-1:20,1" +
"52,-1:2,225,-1:4,225:7,65,225:12,-1:2,225,-1:20,152,-1:2,225,-1:4,225:4,66," +
"225:15,-1:2,225,-1:20,152,-1:2,225,-1:4,67,225:19,-1:2,225,-1:20,152,-1:2,2" +
"25,-1:4,76,225:4,160,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:19,79,-1:2" +
",225,-1:20,152,-1:2,225,-1:4,225:19,83,-1:2,225,-1:20,152,-1:2,225,-1:4,225" +
":3,84,225:16,-1:2,225,-1:20,152,-1:2,225,-1:4,225:16,85,225:3,-1:2,225,-1:2" +
"0,152,-1:2,225,-1:4,225:16,86,225:3,-1:2,225,-1:20,152,-1:2,225,-1:4,225:5," +
"87,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:8,88,225:11,-1:2,225,-1:20,1" +
"52,-1:2,225,-1:4,225:7,89,225:12,-1:2,225,-1:20,152,-1:2,225,-1:4,225:16,90" +
",225:3,-1:2,225,-1:20,152,-1:2,225,-1:4,225:3,91,225:16,-1:2,225,-1:20,152," +
"-1:2,225,-1:4,225:3,92,225:16,-1:2,225,-1:20,152,-1:2,225,-1:4,93,225:19,-1" +
":2,225,-1:20,152,-1:2,225,-1:4,225:4,94,225:15,-1:2,225,-1:20,152,-1:2,225," +
"-1:4,225:8,95,225:11,-1:2,225,-1:20,152,-1:2,225,-1:4,225:7,96,225:12,-1:2," +
"225,-1:20,152,-1:2,225,-1:4,225:8,97,225:11,-1:2,225,-1:20,152,-1:2,225,-1:" +
"4,225:9,98,225:10,-1:2,225,-1:20,152,-1:2,225,-1:4,225:8,99,225:11,-1:2,225" +
",-1:20,152,-1:2,225,-1:4,225:9,100,225:10,-1:2,225,-1:20,152,-1:2,225,-1:4," +
"225:8,101,225:11,-1:2,225,-1:20,152,-1:2,225,-1:4,225:7,102,225:12,-1:2,225" +
",-1:20,152,-1:2,225,-1:4,225:8,103,225:11,-1:2,225,-1:20,152,-1:2,225,-1:4," +
"105,225:3,211,225:15,-1:2,225,-1:20,152,-1:2,225,-1:4,225,106,225:18,-1:2,2" +
"25,-1:20,152,-1:2,225,-1:4,225,107,225:18,-1:2,225,-1:20,152,-1:2,225,-1:4," +
"225:3,108,225:16,-1:2,225,-1:20,152,-1:2,225,-1:4,225:3,109,225:16,-1:2,225" +
",-1:20,152,-1:2,225,-1:4,225:9,110,225:10,-1:2,225,-1:20,152,-1:2,225,-1:4," +
"225:2,111,225:17,-1:2,225,-1:20,152,-1:2,225,-1:4,225:3,112,225:16,-1:2,225" +
",-1:20,152,-1:2,225,-1:4,225:11,113,225:8,-1:2,225,-1:20,152,-1:2,225,-1:4," +
"225:8,114,225:11,-1:2,225,-1:20,152,-1:2,225,-1:4,225:16,115,225:3,-1:2,225" +
",-1:20,152,-1:2,225,-1:4,225:9,116,225:10,-1:2,225,-1:20,152,-1:2,225,-1:4," +
"225:5,117,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:9,118,225:10,-1:2,225" +
",-1:20,152,-1:2,225,-1:4,225:8,119,225:11,-1:2,225,-1:20,152,-1:2,225,-1:4," +
"225:11,120,225:8,-1:2,225,-1:20,152,-1:2,225,-1:4,225:7,121,225:12,-1:2,225" +
",-1:20,152,-1:2,225,-1:4,225:9,122,225:10,-1:2,225,-1:20,152,-1:2,225,-1:4," +
"123,225:19,-1:2,225,-1:20,152,-1:2,225,-1:4,225:11,124,225:8,-1:2,225,-1:20" +
",152,-1:2,225,-1:4,225:17,125,225:2,-1:2,225,-1:20,152,-1:2,225,-1:4,225:5," +
"126,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,128,225:13,162,225:5,-1:2,225,-" +
"1:20,152,-1:2,225,-1:4,225:2,129,225:17,-1:2,225,-1:20,152,-1:2,225,-1:4,22" +
"5:2,221,225:17,-1:2,225,-1:20,152,-1:2,225,-1:4,152:20,-1:2,152,-1:20,152,-" +
"1:2,152,-1:4,225:5,213,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:18,164,2" +
"25,-1:2,225,-1:20,152,-1:2,225,-1:4,225:12,222,225:7,-1:2,225,-1:20,152,-1:" +
"2,225,-1:4,225:8,165,225:11,-1:2,225,-1:20,152,-1:2,225,-1:4,225:11,130,225" +
":8,-1:2,225,-1:20,152,-1:2,225,-1:4,225:9,131,225:10,-1:2,225,-1:20,152,-1:" +
"2,225,-1:4,225:8,166,225:10,167,-1:2,168,-1:20,152,-1:2,225,-1:4,225:4,169," +
"225:15,-1:2,225,-1:20,152,-1:2,225,-1:4,225,223,225:18,-1:2,225,-1:20,152,-" +
"1:2,225,-1:4,225:9,170,225:10,-1:2,225,-1:20,152,-1:2,225,-1:4,173,225:19,-" +
"1:2,225,-1:20,152,-1:2,225,-1:4,225:5,174,225:14,-1:2,225,-1:20,152,-1:2,22" +
"5,-1:4,225:7,176,225:12,-1:2,225,-1:20,152,-1:2,225,-1:4,225:5,132,225:14,-" +
"1:2,225,-1:20,152,-1:2,225,-1:4,225:2,177,225:17,-1:2,225,-1:20,152,-1:2,22" +
"5,-1:4,225:5,133,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:17,178,225:2,-" +
"1:2,225,-1:20,152,-1:2,225,-1:4,225:2,134,225:17,-1:2,225,-1:20,152,-1:2,22" +
"5,-1:4,225:9,180,225:10,-1:2,225,-1:20,152,-1:2,225,-1:4,225:15,135,225:4,-" +
"1:2,225,-1:20,152,-1:2,225,-1:4,225:4,181,225:15,-1:2,225,-1:20,152,-1:2,22" +
"5,-1:4,225:4,136,225:15,-1:2,225,-1:20,152,-1:2,225,-1:4,225:3,137,225:16,-" +
"1:2,225,-1:20,152,-1:2,225,-1:4,225:4,138,225:15,-1:2,225,-1:20,152,-1:2,22" +
"5,-1:4,225:5,182,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,183,225:19,-1:2,22" +
"5,-1:20,152,-1:2,225,-1:4,225:2,217,225:17,-1:2,225,-1:20,152,-1:2,225,-1:4" +
",225:17,184,225:2,-1:2,225,-1:20,152,-1:2,225,-1:4,225:2,185,225:3,186,225," +
"187,225:9,188,225,-1:2,225,-1:20,152,-1:2,225,-1:4,225:9,190,225:10,-1:2,22" +
"5,-1:20,152,-1:2,225,-1:4,225:17,139,225:2,-1:2,225,-1:20,152,-1:2,225,-1:4" +
",225:5,140,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,192,225:6,193,225:12,-1:" +
"2,225,-1:20,152,-1:2,225,-1:4,225:7,141,225:12,-1:2,225,-1:20,152,-1:2,225," +
"-1:4,225:5,142,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:5,218,225:14,-1:" +
"2,225,-1:20,152,-1:2,225,-1:4,225:4,194,225:15,-1:2,225,-1:20,152,-1:2,225," +
"-1:4,225:17,195,225:2,-1:2,225,-1:20,152,-1:2,225,-1:4,225:3,143,225:16,-1:" +
"2,225,-1:20,152,-1:2,225,-1:4,225:10,196,225:9,-1:2,225,-1:20,152,-1:2,225," +
"-1:4,225:9,197,225:10,-1:2,225,-1:20,152,-1:2,225,-1:4,225:12,199,225:7,-1:" +
"2,225,-1:20,152,-1:2,225,-1:4,225:5,144,225:14,-1:2,225,-1:20,152,-1:2,225," +
"-1:4,219,225:19,-1:2,225,-1:20,152,-1:2,225,-1:4,225:8,200,225:11,-1:2,225," +
"-1:20,152,-1:2,225,-1:4,225:8,145,225:11,-1:2,225,-1:20,152,-1:2,225,-1:4,2" +
"25:7,201,225:12,-1:2,225,-1:20,152,-1:2,225,-1:4,225:5,203,225:14,-1:2,225," +
"-1:20,152,-1:2,225,-1:4,225:4,204,225:15,-1:2,225,-1:20,152,-1:2,225,-1:4,2" +
"25:5,146,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:9,205,225:10,-1:2,225," +
"-1:20,152,-1:2,225,-1:4,225:5,206,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,2" +
"25:5,147,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:8,207,225:11,-1:2,225," +
"-1:20,152,-1:2,225,-1:4,225:3,208,225:16,-1:2,225,-1:20,152,-1:2,225,-1:4,2" +
"25:13,209,225:6,-1:2,225,-1:20,152,-1:2,225,-1:4,225:14,148,225:5,-1:2,225," +
"-1:20,152,-1:2,225,-1:4,225:5,153,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,2" +
"25:5,163,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:8,214,225:11,-1:2,225," +
"-1:20,152,-1:2,225,-1:4,225:4,172,225:15,-1:2,225,-1:20,152,-1:2,225,-1:4,2" +
"25:5,215,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:9,216,225:10,-1:2,225," +
"-1:20,152,-1:2,225,-1:4,225:5,189,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,2" +
"25:2,191,225:17,-1:2,225,-1:20,152,-1:2,225,-1:4,225:9,198,225:10,-1:2,225," +
"-1:20,152,-1:2,225,-1:4,225:8,202,225:11,-1:2,225,-1:20,152,-1:2,225,-1:4,2" +
"25:9,154,225:6,155,225:3,-1:2,225,-1:20,152,-1:2,225,-1:4,225:5,171,225:14," +
"-1:2,225,-1:20,152,-1:2,225,-1:4,225:4,175,225:15,-1:2,225,-1:20,152,-1:2,2" +
"25,-1:4,225:5,179,225:14,-1:2,225,-1:20,152,-1:2,225,-1:4,225:5,156,225:14," +
"-1:2,225,-1:20,152,-1:2,225,-1:4,225:6,212,225:13,-1:2,225,-1:20,152,-1:2,2" +
"25,-1:4,157,225:2,158,225:16,-1:2,225,-1:20,152,-1:2,225,-1:4,225:5,159,225" +
":14,-1:2,225,-1:20,152,-1:2,225,-1:3");

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
						{ return new Symbol(sym.tCorchOpen,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{ return new Symbol(sym.tCorchClose,yyline,yychar, yytext());}
					case -5:
						break;
					case 5:
						{ return new Symbol(sym.tInterrogacion,yyline,yychar, yytext());}
					case -6:
						break;
					case 6:
						{ return new Symbol(sym.tDosPuntos,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{ return new Symbol(sym.tPunto,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.tIgual,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.tPtoComa,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{ return new Symbol(sym.tComa,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.tMas,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{ return new Symbol(sym.tMenos,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{ return new Symbol(sym.tDiv,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{ return new Symbol(sym.tPor,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{ return new Symbol(sym.tElev,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{ return new Symbol(sym.tParOpen,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.tParClose,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{ return new Symbol(sym.tLlvOpen,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.tLlvClose,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.tMayorQue,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.tMenorQue,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.tNot,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{
           			//System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresFS.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -24:
						break;
					case 24:
						{ return new Symbol(sym.tEntero,yyline,yychar, yytext());}
					case -25:
						break;
					case 25:
						{yychar=1;}
					case -26:
						break;
					case 26:
						{}
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.tSi,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{ return new Symbol(sym.tIgualDoble,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.tSumaAsig,yyline,yychar, yytext());}
					case -30:
						break;
					case 30:
						{ return new Symbol(sym.tAumen,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.tRestaAsig,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.tDecremen,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.tDiviAsig,yyline,yychar, yytext());}
					case -34:
						break;
					case 34:
						{yybegin(COMENT_MULTI_LINE);}
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.tMultipliAsig,yyline,yychar, yytext());}
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.tMayorIgual,yyline,yychar, yytext());}
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.tMenorIgual,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.tDistinto,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.tAnd,yyline,yychar, yytext());}
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.tOr,yyline,yychar, yytext());}
					case -41:
						break;
					case 41:
						{ return new Symbol(sym.tCad,yyline,yychar, (yytext()).substring(1,yytext().length()-1));}
					case -42:
						break;
					case 42:
						{ return new Symbol(sym.tVar,yyline,yychar, yytext());}
					case -43:
						break;
					case 43:
						{yychar=1;}
					case -44:
						break;
					case 44:
						{ return new Symbol(sym.tDec,yyline,yychar, yytext());}
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.tCaso,yyline,yychar, yytext());}
					case -46:
						break;
					case 46:
						{ return new Symbol(sym.tSino,yyline,yychar, yytext());}
					case -47:
						break;
					case 47:
						{ return new Symbol(sym.tFalse,yyline,yychar, yytext());}
					case -48:
						break;
					case 48:
						{ return new Symbol(sym.res_alClick,yyline,yychar, yytext());}
					case -49:
						break;
					case 49:
						{ return new Symbol(sym.res_maximo,yyline,yychar, yytext());}
					case -50:
						break;
					case 50:
						{ return new Symbol(sym.res_minimo,yyline,yychar, yytext());}
					case -51:
						break;
					case 51:
						{ return new Symbol(sym.tBreak,yyline,yychar, yytext());}
					case -52:
						break;
					case 52:
						{ return new Symbol(sym.tDefecto,yyline,yychar, yytext());}
					case -53:
						break;
					case 53:
						{ return new Symbol(sym.tFuncion,yyline,yychar, yytext());}
					case -54:
						break;
					case 54:
						{ return new Symbol(sym.res_leerGxml,yyline,yychar, yytext());}
					case -55:
						break;
					case 55:
						{ return new Symbol(sym.res_Invertir,yyline,yychar, yytext());}
					case -56:
						break;
					case 56:
						{ return new Symbol(sym.tImprimir,yyline,yychar, yytext());}
					case -57:
						break;
					case 57:
						{ return new Symbol(sym.tReturn,yyline,yychar, yytext());}
					case -58:
						break;
					case 58:
						{ return new Symbol(sym.tTrue,yyline,yychar, yytext());}
					case -59:
						break;
					case 59:
						{ return new Symbol(sym.res_Ascendente,yyline,yychar, yytext());}
					case -60:
						break;
					case 60:
						{ return new Symbol(sym.res_crear_boton,yyline,yychar, yytext());}
					case -61:
						break;
					case 61:
						{ return new Symbol(sym.res_crear_texto,yyline,yychar, yytext());}
					case -62:
						break;
					case 62:
						{ return new Symbol(sym.tSelecciona,yyline,yychar, yytext());}
					case -63:
						break;
					case 63:
						{ return new Symbol(sym.res_Descendente,yyline,yychar, yytext());}
					case -64:
						break;
					case 64:
						{ return new Symbol(sym.res_crear_ventana,yyline,yychar, yytext());}
					case -65:
						break;
					case 65:
						{ return new Symbol(sym.res_crear_caja_texto,yyline,yychar, yytext());}
					case -66:
						break;
					case 66:
						{ return new Symbol(sym.res_crear_contenedor,yyline,yychar, yytext());}
					case -67:
						break;
					case 67:
						{ return new Symbol(sym.res_obtenerPorEtiqueta,yyline,yychar, yytext());}
					case -68:
						break;
					case 68:
						{}
					case -69:
						break;
					case 69:
						{}
					case -70:
						break;
					case 70:
						{yybegin(YYINITIAL);}
					case -71:
						break;
					case 72:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -72:
						break;
					case 73:
						{
           			//System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresFS.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -73:
						break;
					case 74:
						{yychar=1;}
					case -74:
						break;
					case 76:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -75:
						break;
					case 77:
						{
           			//System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresFS.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -76:
						break;
					case 79:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -77:
						break;
					case 80:
						{
           			//System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresFS.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -78:
						break;
					case 82:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -79:
						break;
					case 83:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -80:
						break;
					case 84:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -81:
						break;
					case 85:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -82:
						break;
					case 86:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -83:
						break;
					case 87:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -84:
						break;
					case 88:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -85:
						break;
					case 89:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -86:
						break;
					case 90:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -87:
						break;
					case 91:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -88:
						break;
					case 92:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -89:
						break;
					case 93:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -90:
						break;
					case 94:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -91:
						break;
					case 95:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -92:
						break;
					case 96:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -93:
						break;
					case 97:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -94:
						break;
					case 98:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -95:
						break;
					case 99:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -96:
						break;
					case 100:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -97:
						break;
					case 101:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -98:
						break;
					case 102:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -99:
						break;
					case 103:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -100:
						break;
					case 104:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -101:
						break;
					case 105:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -102:
						break;
					case 106:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -103:
						break;
					case 107:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -104:
						break;
					case 108:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -105:
						break;
					case 109:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -106:
						break;
					case 110:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -107:
						break;
					case 111:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -108:
						break;
					case 112:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -109:
						break;
					case 113:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -110:
						break;
					case 114:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -111:
						break;
					case 115:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -112:
						break;
					case 116:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -113:
						break;
					case 117:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -114:
						break;
					case 118:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -115:
						break;
					case 119:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -116:
						break;
					case 120:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -117:
						break;
					case 121:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -118:
						break;
					case 122:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -119:
						break;
					case 123:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -120:
						break;
					case 124:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -121:
						break;
					case 125:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -122:
						break;
					case 126:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -123:
						break;
					case 127:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -124:
						break;
					case 128:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -125:
						break;
					case 129:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -126:
						break;
					case 130:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -127:
						break;
					case 131:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -128:
						break;
					case 132:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -129:
						break;
					case 133:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -130:
						break;
					case 134:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -131:
						break;
					case 135:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -132:
						break;
					case 136:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -133:
						break;
					case 137:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -134:
						break;
					case 138:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -135:
						break;
					case 139:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -136:
						break;
					case 140:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -137:
						break;
					case 141:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -138:
						break;
					case 142:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -139:
						break;
					case 143:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -140:
						break;
					case 144:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -141:
						break;
					case 145:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -142:
						break;
					case 146:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -143:
						break;
					case 147:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -144:
						break;
					case 148:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -145:
						break;
					case 149:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -146:
						break;
					case 150:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -147:
						break;
					case 151:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -148:
						break;
					case 152:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -149:
						break;
					case 153:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -150:
						break;
					case 154:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -151:
						break;
					case 155:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -152:
						break;
					case 156:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -153:
						break;
					case 157:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -154:
						break;
					case 158:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -155:
						break;
					case 159:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -156:
						break;
					case 160:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -157:
						break;
					case 161:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -158:
						break;
					case 162:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -159:
						break;
					case 163:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -160:
						break;
					case 164:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -161:
						break;
					case 165:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -162:
						break;
					case 166:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -163:
						break;
					case 167:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -164:
						break;
					case 168:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -165:
						break;
					case 169:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -166:
						break;
					case 170:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -167:
						break;
					case 171:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -168:
						break;
					case 172:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -169:
						break;
					case 173:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -170:
						break;
					case 174:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -171:
						break;
					case 175:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -172:
						break;
					case 176:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -173:
						break;
					case 177:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -174:
						break;
					case 178:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -175:
						break;
					case 179:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -176:
						break;
					case 180:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -177:
						break;
					case 181:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -178:
						break;
					case 182:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -179:
						break;
					case 183:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -180:
						break;
					case 184:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -181:
						break;
					case 185:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -182:
						break;
					case 186:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -183:
						break;
					case 187:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -184:
						break;
					case 188:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -185:
						break;
					case 189:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -186:
						break;
					case 190:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -187:
						break;
					case 191:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -188:
						break;
					case 192:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -189:
						break;
					case 193:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -190:
						break;
					case 194:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -191:
						break;
					case 195:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -192:
						break;
					case 196:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -193:
						break;
					case 197:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -194:
						break;
					case 198:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -195:
						break;
					case 199:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -196:
						break;
					case 200:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -197:
						break;
					case 201:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -198:
						break;
					case 202:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -199:
						break;
					case 203:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -200:
						break;
					case 204:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -201:
						break;
					case 205:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -202:
						break;
					case 206:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -203:
						break;
					case 207:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -204:
						break;
					case 208:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -205:
						break;
					case 209:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -206:
						break;
					case 210:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -207:
						break;
					case 211:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -208:
						break;
					case 212:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -209:
						break;
					case 213:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -210:
						break;
					case 214:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -211:
						break;
					case 215:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -212:
						break;
					case 216:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -213:
						break;
					case 217:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -214:
						break;
					case 218:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -215:
						break;
					case 219:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -216:
						break;
					case 220:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -217:
						break;
					case 221:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -218:
						break;
					case 222:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -219:
						break;
					case 223:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -220:
						break;
					case 224:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -221:
						break;
					case 225:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -222:
						break;
					case 226:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -223:
						break;
					case 227:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -224:
						break;
					case 228:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -225:
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
