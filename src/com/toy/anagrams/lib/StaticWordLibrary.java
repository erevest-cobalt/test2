/*
 * Copyright (c) 2010, Oracle.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  * Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/* Anagram Game Application */

package com.toy.anagrams.lib;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Implementation of the logic for the Anagram Game application.
 */
final class StaticWordLibrary extends WordLibrary {

    private static final String[] WORD_LIST = {
        "abstraction",
        "administration",
        "ambiguous",
        "arithmetic",
        "backslash",
        "bitmap",
        "circumstance",
        "combination",
        "consequently",
        "consortium",
        "decrementing",
        "dependency",
        "disambiguate",
        "dynamic",
        "encapsulation",
        "equivalent",
        "expression",
        "facilitate",
        "fragment",
        "hexadecimal",
        "implementation",
        "indistinguishable",
        "inheritance",
        "internet",
        "java",
        "localization",
        "microprocessor",
        "navigation",
        "optimization",
        "parameter",
        "patrick",
        "pickle",
        "polymorphic",
        "rigorously",
        "simultaneously",
        "specification",
        "structure",
        "lexical",
        "likewise",
        "management",
        "manipulate",
        "mathematics",
        "hotjava",
        "vertex",
        "unsigned",
        "traditional"};

//    private static final String[] SCRAMBLED_WORD_LIST = {
//        "batsartcoin",
//        "idsnoaitanitmr",
//        "maibuguos",
//        "ratimhteci",
//        "abkclssha",
//        "ibmtpa",
//        "iccrmutsnaec",
//        "ocbmnitaoni",
//        "ocsnqeeutnyl",
//        "ocsnroitmu",
//        "edrcmeneitgn",
//        "edepdnneyc",
//        "idasbmgiauet",
//        "ydanicm",
//        "neacsplutaoni",
//        "qeiuaveltn",
//        "xerpseisno",
//        "aficilatet",
//        "rfgaemtn",
//        "ehaxedicalm",
//        "milpmeneatitno",
//        "niidtsniugsiahleb",
//        "niehiratcen",
//        "nietnret",
//        "ajav",
//        "olacilazitno",
//        "imrcpoorecssro",
//        "anivagitno",
//        "poitimazitno",
//        "aparemert",
//        "aprtcki",
//        "ipkcel",
//        "opylomprich",
//        "irogorsuyl",
//        "isumtlnaoesuyl",
//        "psceficitaoni",
//        "tsurtcreu",
//        "elixalc",
//        "ilekiwse",
//        "amanegemtn",
//        "aminupalet",
//        "amhtmetacsi",
//        "ohjtvaa",
//        "evtrxe",
//        "nuisngde",
//        "rtdatioialn"
//    };

    final static WordLibrary DEFAULT = new StaticWordLibrary();

    /**
     * Singleton class.
     */
    private StaticWordLibrary() {
    }

    /**
     * Gets the word at a given index.
     * @param idx index of required word
     * @return word at that index in its natural form
     */
    public String getWord(int idx) {
        return WORD_LIST[idx];
    }

    /**
     * Gets the word at a given index in its scrambled form.
     * @param idx index of required word
     * @return word at that index in its scrambled form
     */
    public String getScrambledWord(int idx) {
    	String str = shuffleWord(WORD_LIST[idx]);
        return str;
    }

    /**
     * Shuffles a string.
     * @param str string
     * @return shuffled string
     */
    private String shuffleWord(String str) {
        List<Character> chars = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            chars.add(ch);
        }
        Collections.shuffle(chars);

        StringBuilder builder = new StringBuilder(chars.size());
        for(Character ch: chars) {
            builder.append(ch);
        }
        return builder.toString();
    }

    public String getScrambledWordWithLevel(int index, int level) {
    	return shuffleWord(WORD_LIST[index], level);
    }

    //  Given an original string and a level whose range is 1 to 3.
    //  The higher the level, the more shuffled.
    //  Return the shuffled word corresponding to the level.
    private String shuffleWord(String str, int level) {

    	if (level == 1)
    		return shuffleWordLevelOne(str);
    	/*
    	 * else if (level == 2)
    	 * 	return shuffleWordLevelTwo(str);
    	 * else if (level == 3)
    	 * 	return shuffleWordLevelTree(str);
    	 */
    	return "not yet supported";
    }

    //  Given an original string,
    //  and swapping a set of characters next to each other.
    //  return the swapped string.
    private String shuffleWordLevelOne(String str) {

    	Random rand = new Random();
    	int i = rand.nextInt(str.length()-1);
    	StringBuilder sb = new StringBuilder();

    	for (int j = 0; j < str.length(); j++) {
    		if (i == j) {
    			sb.append(str.charAt(j+1));
    			sb.append(str.charAt(j));
    			j++;
    		} else {
    			sb.append(str.charAt(j));
    		}
    	}
    	return sb.toString();
    }
    /**
     * Gets the number of words in the library.
     * @return the total number of plain/scrambled word pairs in the library
     */
    public int getSize() {
        return WORD_LIST.length;
    }

    /**
     * Checks whether a user's guess for a word at the given index is correct.
     * @param idx index of the word guessed
     * @param userGuess the user's guess for the actual word
     * @return true if the guess was correct; false otherwise
     */
    public boolean isCorrect(int idx, String userGuess) {
        return userGuess.equals(getWord(idx));
    }

}
