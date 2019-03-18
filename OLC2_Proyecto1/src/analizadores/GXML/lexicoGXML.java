/* descargar la ultima version como main.java -> https://www.cs.princeton.edu/~appel/modern/java/JLex/ */
/*------------------- 1era Area: Codigo de usuario ------------------------*/
package analizadores.GXML;
import java_cup.runtime.Symbol;
import GenericXML.ErroresGXML.*;
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
	private final int ACCIONES = 3;
	private final int yy_state_dtrans[] = {
		0,
		171,
		174,
		176
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
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NOT_ACCEPT,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NOT_ACCEPT,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NOT_ACCEPT,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NOT_ACCEPT,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NOT_ACCEPT,
		/* 128 */ YY_NOT_ACCEPT,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NOT_ACCEPT,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NOT_ACCEPT,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NOT_ACCEPT,
		/* 146 */ YY_NOT_ACCEPT,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NOT_ACCEPT,
		/* 150 */ YY_NOT_ACCEPT,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NOT_ACCEPT,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NOT_ACCEPT,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NOT_ACCEPT,
		/* 159 */ YY_NOT_ACCEPT,
		/* 160 */ YY_NOT_ACCEPT,
		/* 161 */ YY_NOT_ACCEPT,
		/* 162 */ YY_NOT_ACCEPT,
		/* 163 */ YY_NOT_ACCEPT,
		/* 164 */ YY_NOT_ACCEPT,
		/* 165 */ YY_NOT_ACCEPT,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NOT_ACCEPT,
		/* 168 */ YY_NOT_ACCEPT,
		/* 169 */ YY_NOT_ACCEPT,
		/* 170 */ YY_NOT_ACCEPT,
		/* 171 */ YY_NOT_ACCEPT,
		/* 172 */ YY_NOT_ACCEPT,
		/* 173 */ YY_NOT_ACCEPT,
		/* 174 */ YY_NOT_ACCEPT,
		/* 175 */ YY_NOT_ACCEPT,
		/* 176 */ YY_NOT_ACCEPT,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NOT_ACCEPT,
		/* 179 */ YY_NOT_ACCEPT,
		/* 180 */ YY_NOT_ACCEPT,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NOT_ACCEPT,
		/* 183 */ YY_NOT_ACCEPT,
		/* 184 */ YY_NOT_ACCEPT,
		/* 185 */ YY_NOT_ACCEPT,
		/* 186 */ YY_NOT_ACCEPT,
		/* 187 */ YY_NOT_ACCEPT,
		/* 188 */ YY_NOT_ACCEPT,
		/* 189 */ YY_NOT_ACCEPT,
		/* 190 */ YY_NOT_ACCEPT,
		/* 191 */ YY_NOT_ACCEPT,
		/* 192 */ YY_NOT_ACCEPT,
		/* 193 */ YY_NOT_ACCEPT,
		/* 194 */ YY_NOT_ACCEPT,
		/* 195 */ YY_NOT_ACCEPT,
		/* 196 */ YY_NOT_ACCEPT,
		/* 197 */ YY_NOT_ACCEPT,
		/* 198 */ YY_NOT_ACCEPT,
		/* 199 */ YY_NOT_ACCEPT,
		/* 200 */ YY_NOT_ACCEPT,
		/* 201 */ YY_NOT_ACCEPT,
		/* 202 */ YY_NOT_ACCEPT,
		/* 203 */ YY_NOT_ACCEPT,
		/* 204 */ YY_NOT_ACCEPT,
		/* 205 */ YY_NOT_ACCEPT,
		/* 206 */ YY_NOT_ACCEPT,
		/* 207 */ YY_NOT_ACCEPT,
		/* 208 */ YY_NOT_ACCEPT,
		/* 209 */ YY_NOT_ACCEPT,
		/* 210 */ YY_NOT_ACCEPT,
		/* 211 */ YY_NOT_ACCEPT,
		/* 212 */ YY_NOT_ACCEPT,
		/* 213 */ YY_NOT_ACCEPT,
		/* 214 */ YY_NOT_ACCEPT,
		/* 215 */ YY_NOT_ACCEPT,
		/* 216 */ YY_NOT_ACCEPT,
		/* 217 */ YY_NOT_ACCEPT,
		/* 218 */ YY_NOT_ACCEPT,
		/* 219 */ YY_NOT_ACCEPT,
		/* 220 */ YY_NOT_ACCEPT,
		/* 221 */ YY_NOT_ACCEPT,
		/* 222 */ YY_NOT_ACCEPT,
		/* 223 */ YY_NOT_ACCEPT,
		/* 224 */ YY_NOT_ACCEPT,
		/* 225 */ YY_NOT_ACCEPT,
		/* 226 */ YY_NOT_ACCEPT,
		/* 227 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"38:9,34,32,38,34,31,38:18,34,38,33,1,2,38:2,33,38:2,30,38:2,29,36,13,35:10," +
"38:2,5,18,3,38:2,12,25,19,17,15,21,28,24,6,38:2,20,7,16,9,8,38,10,26,11,27," +
"14,38,22,23,38:7,12,25,19,17,15,21,28,24,6,38:2,20,7,16,9,8,38,10,26,11,27," +
"14,38,22,23,38,4,38,37,38:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,228,
"0,1,2,1:7,3,4,1:2,5,1:14,6,1:17,7,8,1,9,10,11,12,1,13,1,14,1,15,16,1,17,18," +
"19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43," +
"44,45,46,47,48,49,50,14,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67," +
"68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92," +
"93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,11" +
"3,114,115,116,117,118,119,120,121,122,123,124,125,7,126,127,128,129,130,131" +
",132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,15" +
"0,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,1" +
"69,170,171,172,173,174,175,176,177,178,179,180")[0];

	private int yy_nxt[][] = unpackFromString(181,39,
"1,2,56,3,4,5,64,67,69,56,71,73,75,6,181,77,79,81,7,83,85,87,8,9,56,89,56:5," +
"10:2,91,10,11,56:3,-1:40,55,12,-1:67,10:2,-1,10,-1:39,11,96,-1:34,58,-1:12," +
"147,-1:14,148,-1:18,172,173,172:27,-1,172:8,-1,59,177:3,-1,177:26,-1,177:4," +
"-1,177,-1,177:4,-1,177:25,50,60,177,50,177:2,-1,177,-1,177:4,-1,177:26,61,1" +
"77:4,-1,177,-1,52:4,-1,52:26,-1,52:4,-1,52,-1,52:4,-1,52:25,53,62,52,53,52:" +
"2,-1,52,-1,55:30,14,58,55:6,-1:35,57,-1:4,59:4,175,59:25,51,61,59:4,175,59," +
"-1:31,60:2,-1,60,-1:35,62:2,-1,62,-1:12,97,-1:37,63,-1:9,13,-1:53,61,-1:22," +
"98,-1:28,66,-1:5,68,-1:14,70,-1:33,226,-1:28,72,-1:46,224,-1:33,74,-1:34,99" +
",-1:33,178,-1:5,76,-1:2,179,-1:44,186,-1:33,78,-1:2,183,182,-1:6,206,-1:18," +
"15,-1:47,82,-1:41,102,-1:28,84,-1:5,86,-1:33,104,-1:5,189,-1:34,219,-1:2,22" +
"5,-1:37,209,-1:33,88,-1:17,90,-1:18,105,-1:37,92,-1:60,184,-1:22,180,-1:14," +
"93,-1:27,210,-1:3,208,-1:27,94,-1:39,185,-1:29,95:31,-1:2,95:5,-1:26,221,-1" +
":27,108,-1:33,190,220,-1:28,95:31,-1,16,95:5,-1:9,109,-1:35,110,-1:56,17,-1" +
":23,18,-1:40,112,-1:51,113,-1:23,19,-1:46,116,-1:46,211,-1:22,20,-1:55,120," +
"-1:28,121,-1:32,195,-1:35,124,-1:37,126,-1:41,21,-1:38,22,-1:38,128,-1:58,1" +
"29,-1:21,130,-1:45,222,-1:29,134,-1:4,198,-1:33,23,-1:37,24,-1:40,136,-1:42" +
",25,-1:39,26,-1:31,27,-1:38,28,-1:36,196,-1:46,215,-1:39,29,-1:32,138,-1:45" +
",200,-1:37,139,-1:32,30,-1:43,31,-1:32,142,-1:43,143,-1:39,32,-1:35,144,-1:" +
"41,149,-1:35,33,-1:38,34,-1:35,35,-1:49,36,-1:30,37,-1:36,38,-1:45,202,-1:4" +
"0,217,-1:35,223,-1:28,152,-1:40,203,-1:40,153,-1:39,204,-1:43,158,-1:31,39," +
"-1:38,159,-1:41,40,-1:38,41,-1:45,161,-1:31,162,-1:36,42,-1:54,43,-1:18,164" +
",-1:52,44,-1:35,165,-1:33,166,-1:53,167,-1:31,45,-1:37,205,-1:25,169,-1:41," +
"170,-1:45,46,-1:22,1,172,173,172:27,-1,172:8,-1,47,173,172:27,-1,172:8,1,48" +
",177:3,49,177:25,50,60,177,50,177:2,-1,177,-1,175:30,65,61,175:6,1,52:4,-1," +
"52:25,53,62,52,53,52:2,54,52,-1,177:4,-1,177:26,-1,177:4,-1,177,-1:8,100,-1" +
":52,101,-1:36,107,-1:33,80,-1:34,103,-1:46,188,-1:29,193,-1:54,212,-1:27,19" +
"1,-1:32,115,-1:35,114,-1:43,194,-1:44,122,-1:31,127,-1:35,125,-1:37,214,-1:" +
"44,131,-1:37,137,-1:42,145,-1:40,199,-1:37,216,-1:34,151,-1:41,150,-1:40,15" +
"4,-1:27,155,-1:42,218,-1:37,160,-1:48,168,-1:30,187,-1:42,117,-1:32,119,-1:" +
"35,213,-1:43,118,-1:37,133,-1:34,135,-1:44,132,-1:37,140,-1:43,146,-1:37,20" +
"1,-1:29,156,-1:41,163,-1:40,106,-1:36,123,-1:40,227,-1:38,141,-1:33,157,-1:" +
"43,111,-1:48,207,-1:23,192,-1:44,197,-1:26");

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
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -3:
						break;
					case 3:
						{yybegin(MAYORQUE); return new Symbol(sym.res_MayorQue,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{ yybegin(ACCIONES); return new Symbol(sym.res_llvOpen,yyline,yychar, yytext());}
					case -5:
						break;
					case 5:
						{return new Symbol(sym.res_MenorQue,yyline,yychar, yytext());}
					case -6:
						break;
					case 6:
						{ return new Symbol(sym.res_slash,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{ return new Symbol(sym.res_Igual,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.res_x,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.res_y,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{}
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.tNumerico,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{yybegin(COMENT_MULTI_LINE);}
					case -13:
						break;
					case 13:
						{ return new Symbol(sym.res_id,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{yychar=1;}
					case -15:
						break;
					case 15:
						{ return new Symbol(sym.res_tam,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{ return new Symbol(sym.tCad,yyline,yychar, (yytext()).substring(1,yytext().length()-1));}
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.res_path,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{ return new Symbol(sym.res_tipo,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.res_alto,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.res_dato,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.res_texto,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.res_ancho,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{ return new Symbol(sym.res_color,yyline,yychar, yytext());}
					case -24:
						break;
					case 24:
						{ return new Symbol(sym.res_falso,yyline,yychar, yytext());}
					case -25:
						break;
					case 25:
						{ return new Symbol(sym.res_borde,yyline,yychar, yytext());}
					case -26:
						break;
					case 26:
						{ return new Symbol(sym.res_boton,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.res_minimo,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{ return new Symbol(sym.res_maximo,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.res_accion,yyline,yychar, yytext());}
					case -30:
						break;
					case 30:
						{ return new Symbol(sym.res_enviar,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.res_nombre,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.res_fuente,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.res_ventana,yyline,yychar, yytext());}
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.res_negrita,yyline,yychar, yytext());}
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.res_defecto,yyline,yychar, yytext());}
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.res_control,yyline,yychar, yytext());}
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.res_cursiva,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.res_importar,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.res_verdadero,yyline,yychar, yytext());}
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.res_multimedia,yyline,yychar, yytext());}
					case -41:
						break;
					case 41:
						{ return new Symbol(sym.res_referencia,yyline,yychar, yytext());}
					case -42:
						break;
					case 42:
						{ return new Symbol(sym.res_contenedor,yyline,yychar, yytext());}
					case -43:
						break;
					case 43:
						{ return new Symbol(sym.res_listaDatos,yyline,yychar, yytext());}
					case -44:
						break;
					case 44:
						{ return new Symbol(sym.res_accionFinal,yyline,yychar, yytext());}
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.res_accionInicial,yyline,yychar, yytext());}
					case -46:
						break;
					case 46:
						{ return new Symbol(sym.res_auto_reproduccion,yyline,yychar, yytext());}
					case -47:
						break;
					case 47:
						{ System.out.println ("Comentario: <" +yytext().substring(0,yytext().length()-2)+">");yybegin(YYINITIAL); }
					case -48:
						break;
					case 48:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -49:
						break;
					case 49:
						{yybegin(YYINITIAL); return new Symbol(sym.res_MenorQue,yyline,yychar, yytext());}
					case -50:
						break;
					case 50:
						{}
					case -51:
						break;
					case 51:
						{yychar=1;}
					case -52:
						break;
					case 52:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -53:
						break;
					case 53:
						{}
					case -54:
						break;
					case 54:
						{ yybegin(YYINITIAL); return new Symbol(sym.res_llvClose,yyline,yychar, yytext()); }
					case -55:
						break;
					case 56:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -56:
						break;
					case 57:
						{ return new Symbol(sym.tNumerico,yyline,yychar, yytext());}
					case -57:
						break;
					case 58:
						{yychar=1;}
					case -58:
						break;
					case 59:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -59:
						break;
					case 60:
						{}
					case -60:
						break;
					case 61:
						{yychar=1;}
					case -61:
						break;
					case 62:
						{}
					case -62:
						break;
					case 64:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -63:
						break;
					case 65:
						{yychar=1;}
					case -64:
						break;
					case 67:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -65:
						break;
					case 69:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -66:
						break;
					case 71:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -67:
						break;
					case 73:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -68:
						break;
					case 75:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -69:
						break;
					case 77:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -70:
						break;
					case 79:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -71:
						break;
					case 81:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -72:
						break;
					case 83:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -73:
						break;
					case 85:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -74:
						break;
					case 87:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -75:
						break;
					case 89:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -76:
						break;
					case 91:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -77:
						break;
					case 177:
						{ return new Symbol(sym.tTexto,yyline,yychar, yytext());}
					case -78:
						break;
					case 181:
						{
           			System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresGXML.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -79:
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
