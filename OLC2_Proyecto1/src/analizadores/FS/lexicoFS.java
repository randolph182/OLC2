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
		67
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
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
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
		/* 120 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"40:9,45,43,40,45,44,40:18,45,35,39,40:3,36,39,29,30,27,24,23,25,41,26,38:10" +
",17,22,34,21,33,16,40,12,42,6,14,10,3,42:2,7,42:2,15,18,5,8,19,42,9,13,11,4" +
",20,42:4,1,40,2,28,42,40,12,42,6,14,10,3,42:2,7,42:2,15,18,5,8,19,42,9,13,1" +
"1,4,20,42:4,31,37,32,40:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,121,
"0,1:3,2,1:2,3,1:2,4,5,6,7,1:5,8,9,10,11,12,13:2,14,1:13,15,1,16,1,15:10,1,1" +
"7,1,18,19,20,21,16,22,23,24,25,1,26,27,28,29,30,31,32,33,34,35,36,37,38,39," +
"40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64," +
"65,66,67,68,69,70,15,71,72,73,74,75,76,77,78")[0];

	private int yy_nxt[][] = unpackFromString(79,46,
"1,2,3,4,112:2,87,116,112,118,112:3,58,119,112,5,6,112:2,77,7,8,9,10,11,12,1" +
"3,14,15,16,17,18,19,20,21,22,59,23,63,66:2,112,24,25:2,-1:49,112,120,112:7," +
"88,112:3,-1:2,112:3,-1:17,89,-1:3,112,-1:24,27,-1:45,28,-1:2,29,-1:42,30,-1" +
":3,31,-1:41,32,-1:4,57,33,-1:39,34,-1:45,35,-1:45,36,-1:45,37,-1:60,38,-1:4" +
"7,23,-1:2,61,-1:47,25:3,-1:3,112:2,68,112:10,-1:2,112:3,-1:17,89,-1:3,112,-" +
"1:6,112:13,-1:2,112:3,-1:17,89,-1:3,112,-1:41,42,-1:33,56,-1:20,57:42,41,60" +
",57,-1:3,112:4,26,112:2,92,112:5,-1:2,112:3,-1:17,89,-1:3,112,-1:40,39,-1:5" +
"1,41,-1:5,112:6,40,112:6,-1:2,112:3,-1:17,89,-1:3,112,-1:4,64:38,-1,64:3,-1" +
",64:2,-1,64:38,43,64:3,-1,64:2,-1:3,112:5,44,112:7,-1:2,112:3,-1:17,89,-1:3" +
",112,-1:3,1,54:25,-1,55,54:18,-1:3,112:5,45,112:7,-1:2,112:3,-1:17,89,-1:3," +
"112,-1:6,112:5,46,112:7,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:2,47,112:10,-" +
"1:2,112:3,-1:17,89,-1:3,112,-1:6,112:5,48,112:7,-1:2,112:3,-1:17,89,-1:3,11" +
"2,-1:6,112:6,49,112:6,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:6,50,112:6,-1:2" +
",112:3,-1:17,89,-1:3,112,-1:6,112:6,51,112:6,-1:2,112:3,-1:17,89,-1:3,112,-" +
"1:6,112:5,52,112:7,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:9,53,112:3,-1:2,11" +
"2:3,-1:17,89,-1:3,112,-1:6,112:7,94,112,62,112:3,-1:2,112:3,-1:17,89,-1:3,1" +
"12,-1:6,112:10,65,112:2,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:10,69,112:2,-" +
"1:2,112:3,-1:17,89,-1:3,112,-1:6,112:5,70,112:7,-1:2,112:3,-1:17,89,-1:3,11" +
"2,-1:6,112:8,71,112:4,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:7,72,112:5,-1:2" +
",112:3,-1:17,89,-1:3,112,-1:6,112:4,73,112:8,-1:2,112:3,-1:17,89,-1:3,112,-" +
"1:6,112:9,74,112:3,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:6,75,112:6,-1:2,11" +
"2:3,-1:17,89,-1:3,112,-1:6,112:2,76,112:10,-1:2,112:3,-1:17,89,-1:3,112,-1:" +
"6,112:9,78,112:3,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:12,79,-1:2,112:3,-1:" +
"17,89,-1:3,112,-1:6,89:13,-1:2,89:3,-1:17,89,-1:3,89,-1:6,112:13,-1:2,112,1" +
"13,112,-1:17,89,-1:3,112,-1:6,112:8,96,112:4,-1:2,112:3,-1:17,89,-1:3,112,-" +
"1:6,112:12,97,-1:2,112:3,-1:17,89,-1:3,112,-1:6,114,112:7,117,112:4,-1:2,11" +
"2:3,-1:17,89,-1:3,112,-1:6,112:6,98,112:6,-1:2,112:3,-1:17,89,-1:3,112,-1:6" +
",112:3,99,112:9,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:5,101,112:7,-1:2,112:" +
"3,-1:17,89,-1:3,112,-1:6,112:7,102,112:5,-1:2,112:3,-1:17,89,-1:3,112,-1:6," +
"112:11,105,112,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:4,80,112:8,-1:2,112:3," +
"-1:17,89,-1:3,112,-1:6,112:4,106,112:8,-1:2,112:3,-1:17,89,-1:3,112,-1:6,11" +
"2:6,107,112:6,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:3,115,112:9,-1:2,112:3," +
"-1:17,89,-1:3,112,-1:6,112:3,81,112:9,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112" +
":2,82,112:10,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:9,108,112:3,-1:2,112:3,-" +
"1:17,89,-1:3,112,-1:6,112:13,-1:2,83,112:2,-1:17,89,-1:3,112,-1:6,112:2,84," +
"112:10,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:11,110,112,-1:2,112:3,-1:17,89" +
",-1:3,112,-1:6,112:4,111,112:8,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:7,85,1" +
"12:5,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:5,86,112:7,-1:2,112:3,-1:17,89,-" +
"1:3,112,-1:6,112:6,100,112:6,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:7,103,11" +
"2:5,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:3,109,112:9,-1:2,112:3,-1:17,89,-" +
"1:3,112,-1:6,112:13,-1:2,90,112:2,-1:17,89,-1:3,112,-1:6,112:7,104,112:5,-1" +
":2,112:3,-1:17,89,-1:3,112,-1:6,112:7,91,112:5,-1:2,112:3,-1:17,89,-1:3,112" +
",-1:6,112:7,93,112:5,-1:2,112:3,-1:17,89,-1:3,112,-1:6,112:2,95,112:10,-1:2" +
",112:3,-1:17,89,-1:3,112,-1:3");

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
						{ return new Symbol(sym.tCorchOpen,yyline,yychar, yytext());}
					case -3:
						break;
					case 3:
						{ return new Symbol(sym.tCorchClose,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
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
						{ return new Symbol(sym.tIgual,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.tPtoComa,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.tComa,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{ return new Symbol(sym.tMas,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.tMenos,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{ return new Symbol(sym.tDiv,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{ return new Symbol(sym.tPor,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{ return new Symbol(sym.tElev,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{ return new Symbol(sym.tParOpen,yyline,yychar, yytext());}
					case -16:
						break;
					case 16:
						{ return new Symbol(sym.tParClose,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.tLlvOpen,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{ return new Symbol(sym.tLlvClose,yyline,yychar, yytext());}
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.tMayorQue,yyline,yychar, yytext());}
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.tMenorQue,yyline,yychar, yytext());}
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.tNot,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{
           			//System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresFS.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -23:
						break;
					case 23:
						{ return new Symbol(sym.tEntero,yyline,yychar, yytext());}
					case -24:
						break;
					case 24:
						{yychar=1;}
					case -25:
						break;
					case 25:
						{}
					case -26:
						break;
					case 26:
						{ return new Symbol(sym.tSi,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.tIgualDoble,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{ return new Symbol(sym.tSumaAsig,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.tAumen,yyline,yychar, yytext());}
					case -30:
						break;
					case 30:
						{ return new Symbol(sym.tRestaAsig,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.tDecremen,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.tDiviAsig,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{yybegin(COMENT_MULTI_LINE);}
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.tMultipliAsig,yyline,yychar, yytext());}
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.tMayorIgual,yyline,yychar, yytext());}
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.tMenorIgual,yyline,yychar, yytext());}
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.tDistinto,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.tAnd,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.tOr,yyline,yychar, yytext());}
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.tVar,yyline,yychar, yytext());}
					case -41:
						break;
					case 41:
						{yychar=1;}
					case -42:
						break;
					case 42:
						{ return new Symbol(sym.tDec,yyline,yychar, yytext());}
					case -43:
						break;
					case 43:
						{ return new Symbol(sym.tCad,yyline,yychar, (yytext()).substring(1,yytext().length()-1));}
					case -44:
						break;
					case 44:
						{ return new Symbol(sym.tCaso,yyline,yychar, yytext());}
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.tSino,yyline,yychar, yytext());}
					case -46:
						break;
					case 46:
						{ return new Symbol(sym.tFalse,yyline,yychar, yytext());}
					case -47:
						break;
					case 47:
						{ return new Symbol(sym.tFuncion,yyline,yychar, yytext());}
					case -48:
						break;
					case 48:
						{ return new Symbol(sym.tDefecto,yyline,yychar, yytext());}
					case -49:
						break;
					case 49:
						{ return new Symbol(sym.tBreak,yyline,yychar, yytext());}
					case -50:
						break;
					case 50:
						{ return new Symbol(sym.tImprimir,yyline,yychar, yytext());}
					case -51:
						break;
					case 51:
						{ return new Symbol(sym.tReturn,yyline,yychar, yytext());}
					case -52:
						break;
					case 52:
						{ return new Symbol(sym.tTrue,yyline,yychar, yytext());}
					case -53:
						break;
					case 53:
						{ return new Symbol(sym.tSelecciona,yyline,yychar, yytext());}
					case -54:
						break;
					case 54:
						{}
					case -55:
						break;
					case 55:
						{}
					case -56:
						break;
					case 56:
						{yybegin(YYINITIAL);}
					case -57:
						break;
					case 58:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -58:
						break;
					case 59:
						{
           			//System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresFS.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -59:
						break;
					case 60:
						{yychar=1;}
					case -60:
						break;
					case 62:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -61:
						break;
					case 63:
						{
           			//System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresFS.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -62:
						break;
					case 65:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -63:
						break;
					case 66:
						{
           			//System.out.println("Error lexico en : "+yytext() + ", en la linea: "+yyline+", en la columna: "+yychar);
           			ManejadorErroresFS.getInstance().setErrorLexico(yyline,yychar, "problemas con el caracter = " + yytext());
				}
					case -64:
						break;
					case 68:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -65:
						break;
					case 69:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -66:
						break;
					case 70:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -67:
						break;
					case 71:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -68:
						break;
					case 72:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -69:
						break;
					case 73:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -70:
						break;
					case 74:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -71:
						break;
					case 75:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -72:
						break;
					case 76:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -73:
						break;
					case 77:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -74:
						break;
					case 78:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -75:
						break;
					case 79:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -76:
						break;
					case 80:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
					case -77:
						break;
					case 81:
						{ return new Symbol(sym.tId,yyline,yychar, yytext());}
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
