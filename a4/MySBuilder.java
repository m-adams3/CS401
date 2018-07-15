// Michael Adams
// TuTh 1pm

//import ArrayList for split()
import java.util.*;

public class MySBuilder extends SimpleSBuilder
{
	//LIST OF INHERITED DATA & METHODS
	
	//protected char [] data;
	//protected int len;
	
	//public SimpleSBuilder()
	//public SimpleSBuilder(int capacity)
	//public SimpleSBuilder(String str)
	
	//public char charAt(int index)
	//public String toString()
	//public int capacity()
	//public int length()
	//public boolean equals(SimpleSBuilder arg)
	//protected void resize(int newcap)
	
	
	//CONSTRUCTOR #1 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder(int capacity)
	{
		super(capacity);
	}
	
	//CONSTRUCTOR #2 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder(String str)
	{
		super(str);
	}
	
	//CONSTRUCTOR #3 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder(char [] str)
	{
		//LOGIC
		// we want this to override SimpleSBuilder(String str)
		// SimpleSBuilder(String str) doubles length of str so we dont need to handle that here
		// all we have to do here is convert a char array to a string and call super(String str)
		
		super(new String(str));
		
		//INSTRUCTIONS
		// Build a new MySBuilder from an array of char
		// Array length should be twice the length of str
	}
	
	//CONSTRUCTOR #4 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder(MySBuilder old)
	{
		//LOGIC
		// use the toString() included in SimpleSBuilder
		
		super(new String(old.toString()));
		
		//INSTRUCTIONS
		// Copy constructor
		// Array length should be twice the logical size of old
	}
	
	//INSTRUCTIONS
	// For all append methods, if the additional characters will
	// fit in the array, just add them.  If they will not fit, resize
	// the array to twice the logical size of the resulting SBuilder
	// (so it is exactly 1/2 full following the operation)
	
	//APPEND #1 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder append(String str)
	{
		//LOGIC
		// 1. find length of subStr
		// 2. find physical and logical size of data char array
		// 3. find newLogLen
		// 4. if it fits then just append
		// 5. if not then resize to 2x (dataLogLen + subStrLen) then append
		// 6. update this.len with newLogLen
		// 7. return MySBuilder obj
		
		// 1. find size of subStr
		int subStrLen;
		subStrLen = str.length();
		
		// 2. find physical and logical size of data char array
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		// 3. find newLogLen
		int newLogLen;
		newLogLen = subStrLen + dataLogLen;
		
		// 4. if it fits then just append
		if (dataPhysLen - dataLogLen >= subStrLen) {
			int j = 0;
			for (int i = dataLogLen; i < (dataLogLen + subStrLen); i++) {
				data[i] = str.charAt(j);
				j++;
			}
		}
		// 5. if not then resize to 2x (dataLogLen + subStrLen) then append
		else if (dataPhysLen - dataLogLen < subStrLen) {
			int newCap = 2 * (dataLogLen + subStrLen);
			resize(newCap);
			
			int j = 0;
			for (int i = dataLogLen; i < (dataLogLen + subStrLen); i++) {
				data[i] = str.charAt(j);
				j++;
			}
		}
		
		// 6. update this.len with newLogLen
		this.len = newLogLen;
		
		// 7. return MySBuilder obj
		return this;
		
		//INSTRUCTIONS
		// Append str to end of this MySBuilder
		// return this
	}
	
	//APPEND #2 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder append(MySBuilder S)
	{
		//LOGIC
		// 1. find length of S.data
		// 2. find physical and logical size of data char array
		// 3. find newLogLen
		// 4. if it fits then just append
		// 5. if not then resize to 2x (dataLogLen + SLen) then append
		// 6. update this.len with newLogLen
		// 7. return MySBuilder obj
		
		// 1. find length of S.data
		int SLen;
		SLen = S.len;
		
		// 2. find physical and logical size of data char array
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		// 3. find newLogLen
		int newLogLen;
		newLogLen = SLen + dataLogLen;
		
		// 4. if it fits then just append
		if (dataPhysLen - dataLogLen >= SLen) {
			int j = 0;
			for (int i = dataLogLen; i < (dataLogLen + SLen); i++) {
				data[i] = S.data[j];
				j++;
			}
		}
		// 5. if not then resize to 2x (dataLogLen + SLen) then append
		else if (dataPhysLen - dataLogLen < SLen) {
			int newCap = 2 * (dataLogLen + SLen);
			resize(newCap);
			
			int j = 0;
			for (int i = dataLogLen; i < (dataLogLen + SLen); i++) {
				data[i] = S.data[j];
				j++;
			}
		}
		
		// 6. update this.len with newLogLen
		this.len = newLogLen;
		
		// 7. return MySBuilder obj
		return this;
		
		//INSTRUCTIONS
		// Append S to end of this MySBuilder
		// return this
	}
	
	//APPEND #3 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder append(char [] str)
	{
		//LOGIC
		// 1. find length of str char array
		// 2. find physical and logical size of data char array
		// 3. find newLogLen
		// 4. if it fits then just append
		// 5. if not then resize to 2x (dataLogLen + strLen) then append
		// 6. update this.len with newLogLen
		// 7. return MySBuilder obj
		
		// 1. find length of str char array
		int strLen;
		strLen = str.length;
		
		// 2. find physical and logical size of data char array
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		// 3. find newLogLen
		int newLogLen;
		newLogLen = strLen + dataLogLen;
		
		// 4. if it fits then just append
		if (dataPhysLen - dataLogLen >= strLen) {
			int j = 0;
			for (int i = dataLogLen; i < (dataLogLen + strLen); i++) {
				data[i] = str[j];
				j++;
			}
		}
		// 5. if not then resize to 2x (dataLogLen + strLen) then append
		else if (dataPhysLen - dataLogLen < strLen) {
			int newCap = 2 * (dataLogLen + strLen);
			resize(newCap);
			
			int j = 0;
			for (int i = dataLogLen; i < (dataLogLen + strLen); i++) {
				data[i] = str[j];
				j++;
			}
		}
		
		// 6. update this.len with newLogLen
		this.len = newLogLen;
		
		// 7. return MySBuilder obj
		return this;
		
		//INSTRUCTIONS
		// append str to end of this MySBuilder
		// return this
	}
	
	//APPEND #4 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder append(char c)
	{
		//LOGIC
		// 1. find physical and logical size of data char array
		// 2. find newLogLen
		// 3. if it fits then just append
		// 4. if not then resize to 2x (dataLogLen + 1) then append
		// 5. update this.len with newLogLen
		// 6. return MySBuilder obj
		
		// 1. find physical and logical size of data char array
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		// 2. find newLogLen
		int newLogLen;
		newLogLen = 1 + dataLogLen;
		
		// 3. if it fits then just append
		if (dataPhysLen - dataLogLen >= 1) {
			data[dataLogLen] = c;
		}
		// 4. if not then resize to 2x (dataLogLen + 1) then append
		else if (dataPhysLen - dataLogLen < 1) {
			int newCap = 2 * (dataLogLen + 1);
			resize(newCap);
			data[dataLogLen] = c;
		}
		
		// 5. update this.len with newLogLen
		this.len = newLogLen;
		
		// 6. return MySBuilder obj
		return this;
		
		//INSTRUCTIONS
		// append c to end of this MySBuilder
		// return this
	}
	
	//DELETE - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder delete(int start, int end)
	{
		//WE ARE ASSUMING THAT START != END BECAUSE IN THAT CASE THE USER SHOULD CHOOSE DELETECHARAT
		
		//LOGIC
		// 1. find physical and logical size of data char array
		// 2. initialize newLogLen bc stupid variable scope
		// 3. deletes chars, shifts remaining, finds newLogLen
		// 4. update this.len with newLogLen
		// 5. return MySBuidler obj
		
		// 1. find physical and logical size of data char array
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		// 2. initialize newLogLen bc stupid variable scope
		int newLogLen = 0;
		
		// 3. deletes chars, shifts remaining, finds newLogLen - for index range in beginning or middle
		int a = start; //this is unneccessary but I like it
		int b = end; //this is unneccessary but I like it
		if (a >= 0 && a < (dataLogLen - 1) && b < dataLogLen) {
			//shifts chars to fill in deleted range
			int j = 0;
			for (int i = b; i < dataLogLen; i++) {
				data[a+j] = data[b+j];
				j++;
			}
			//find newLogLen
			newLogLen = dataLogLen - (b - a);
			//copy data char array to temp char array which includes unwanted chars
			char[] temp = new char[dataPhysLen];
			for (int i = 0; i < dataPhysLen; i++) {
				temp[i] = data[i];
			}
			//make a new data char array the same size as the old one but only copy in desired chars
			this.data = new char[dataPhysLen];
			for (int i = 0; i < newLogLen; i++) {
				this.data[i] = temp[i];
			}
		}
		// 3. deletes chars, shifts remaining, finds newLogLen - for index range at end 
		else if (a >= 0 && a < dataLogLen && b >= dataLogLen) {
			//copy data char array to temp char array which includes unwanted chars
			char[] temp = new char[dataPhysLen];
			for (int i = 0; i < dataPhysLen; i++) {
				temp[i] = data[i];
			}
			//make a new data char array the same size as the old one but only copy in desired chars
			this.data = new char[dataPhysLen];
			for (int i = 0; i < a; i++) {
				this.data[i] = temp[i];
			}
			//find newLogLen
			newLogLen = a;
		}
		
		// 3. update this.len with newLogLen
		this.len = newLogLen;
		
		// 4. return MySBuidler obj
		return this;
		
		//INSTRUCTIONS
		// delete characters from start (inclusive) to end (exclusive)
		// from this MySBuilder, shifting to fill in the gap.  If range
		// is invalid do nothing.
		// return this
	}
	
	//DELETECHARAT - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder deleteCharAt(int index)
	{
		//LOGIC
		// 1. find physical length of data char array
		// 2. if index is valid, remove char at index and shift remaining, find newLogLen
		// 3. update this.len with newLogLen
		// 4. return MySBuidler obj
		
		// 1. find physical length of data char array
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		// 2. if index is valid, find newLogLen, remove char at index, shift remaining - cases wehre index is in beginning or middle
		int newLogLen = dataLogLen;
		if (index >= 0 && index < (dataLogLen - 1)) {
			//find newLogLen
			newLogLen = dataLogLen - 1;
			//shifts chars to fill in for deleted char
			for (int i = index; i < newLogLen; i++) {
				data[i] = data[i + 1];
			}
			//copy data char array to temp char array which includes unwanted chars
			char[] temp = new char[dataPhysLen];
			for (int i = 0; i < dataPhysLen; i++) {
				temp[i] = data[i];
			}
			//make a new data char array the same size as the old one but only copy in desired chars
			data = new char[dataPhysLen];
			for (int i = 0; i < newLogLen; i++) {
				data[i] = temp[i];
			}
		}
		// 2. if index is valid, find newLogLen, remove char at index, shift remaining - cases where index is at end
		if (index == (dataLogLen - 1)) {
			//find newLogLen
			newLogLen = dataLogLen - 1;
			//copy data char array to temp char array which includes unwanted char
			char[] temp = new char[dataPhysLen];
			for (int i = 0; i < dataPhysLen; i++) {
				temp[i] = data[i];
			}
			//make a new data char array the same size as the old one but only copy in desired chars
			data = new char[dataPhysLen];
			for (int i = 0; i < newLogLen; i++) {
				data[i] = temp[i];
			}
		}
		
		// 3. update this.len with newLogLen
		this.len = newLogLen;
		
		// 4. return MySBuidler obj
		return this;
		
		//INSTRUCTIONS
		// delete char at index from this MySBuilder, shifting to fill in the 
		// gap.  If index is invalid do nothing
		// return this
	}
	
	//INDEXOF #1 - DONE ---------------------------------------------------------------------------------------------
	public int indexOf(String str)
	{
		//LOGIC
		// 1. declare vars
		// 2. loop through data to find index, if found fill test char array, compare to str
		// 3. return index if match, return -1 if no match
		
		// 1. declare vars
		int count = 0;
		int start = 0;
		int inter = 0;
		int loop = 0;
		boolean check = false;
		char[] test = new char[str.length()];
		
		
		outerloop: //use label to break when necessary
		// 2. loop through data to find index, if found fill test char array, compare to str
		for (int i = 0; i < this.len; i++) {	
			
			//if data[i] is equal to first char in str
			if (data[i] == str.charAt(0)) {
				//index of start of substring
				start = i;
				//fill test array with appropriate chars from data		
				for (int j = 0; j < test.length; j++) {
					test[j] = data[i + j];
				}		
				//compare all values of test array to str
				count = 0;
				for (int k = 0; k < test.length; k++) {
					if(test[k] == str.charAt(k)) {
						count++;
					}
				}
				//if test char values equal str for all values
				if (count == str.length()) {
					check = true;
				}
				//if not equal break
				if (check) {
					break outerloop;
				}				
			}	
		}
		// 3. return index if match, return -1 if no match
		if (check) {
			return start;
		}
		else {
			return -1;
		}

		//INSTRUCTIONS
		// return the beginning index where str matches a substring within
		// this MySBuidler.  If there is no match return -1.
	}

	//INDEXOF #2 - DONE ---------------------------------------------------------------------------------------------
	public int indexOf(String str, int fromIndex)
	{
		//LOGIC
		// 1. declare vars
		// 2. loop through data starting at offset to find index, if found fill test char array, compare to str
		// 3. return index if match, return -1 if no match
		
		// 1. declare vars
		int count = 0;
		int start = 0;
		int inter = 0;
		int loop = 0;
		boolean check = false;
		char[] test = new char[str.length()];
		
		
		outerloop: //use label to break when necessary
		// 2. loop through data starting at offset to find index, if found fill test char array, compare to str
		for (int i = fromIndex; i < this.len; i++) {	
			
			//if data[i] is equal to first char in str
			if (data[i] == str.charAt(0)) {
				//index of start of substring
				start = i;
				//fill test array with appropriate chars from data		
				for (int j = 0; j < test.length; j++) {
					test[j] = data[i + j];
				}		
				//compare all values of test array to str
				count = 0;
				for (int k = 0; k < test.length; k++) {
					if(test[k] == str.charAt(k)) {
						count++;
					}
				}
				//if test char values equal str for all values
				if (count == str.length()) {
					check = true;
				}
				//if not equal break
				if (check) {
					break outerloop;
				}				
			}	
		}
		// 3. return index if match, return -1 if no match
		if (check) {
			return start;
		}
		else {
			return -1;
		}

		//INSTRUCTIONS
		// return the beginning index where str matches a substring within
		// this MySBuilder, starting the search at location fromIndex.
		// If there is no match return -1.
	}

	//INSERT #1 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder insert(int offset, String str)
	{
		//LOGIC
		// 1. find strLogLen
		// 2. find dataLogLen and dataPhysLen
		// 3. if index is valid - find newLogLen, copy data after offset, check for resize, insert new chars, copy in old data after offset
		// 4. update this.len with newLogLen
		// 5. return MySBuidler obj
		
		// 1. find strLogLen
		int strLogLen;
		strLogLen = str.length();
		
		// 2. find dataLogLen and dataPhysLen
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		int newLogLen = dataLogLen;
		// 3. if index is valid - find newLogLen, copy data after offset, check for resize, insert new chars, copy in old data after offset
		if (offset >= 0 && offset <= dataLogLen) {
			//find newLogLen
			newLogLen = strLogLen + dataLogLen;
			//copy in data after offset index
			char[] temp = new char[dataLogLen];
			int j = 0;
			for (int i = offset; i < dataLogLen; i++) {
				temp[j] = data[i];
				j++;
			}
			//check for resize
			if (newLogLen > dataPhysLen) {
				int newCap = 2 * newLogLen;
				resize(newCap);
			}
			//insert chars
			int k = 0;
			for (int i = offset; i < (offset + strLogLen); i++) {
				data[i] = str.charAt(k);
				k++;
			}
			//copy in rest of data
			int l = 0;
			for (int i = (offset + strLogLen); i < newLogLen; i++) {
				data[i] = temp[l];
				l++;
			}
		}
		
		// 4. update this.len with newLogLen
		this.len = newLogLen;
		
		// 5. return MySBuidler obj
		return this;
		
		//INSTRUCTIONS
		// insert str into this MySBuilder, beginning at index offset.  Shift
		// any existing characters to the right to make space.  If offset < 0
		// or offset > len do nothing.  If the array must be resized, it should
		// be twice the size of the resulting string.
		// return this
	}
	
	//INSERT #2 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder insert(int offset, char [] str)
	{
		//LOGIC
		// 1. find strLogLen
		// 2. find dataLogLen and dataPhysLen
		// 3. if index is valid - find newLogLen, copy data after offset, check for resize, insert new chars, copy in old data after offset
		// 4. update this.len with newLogLen
		// 5. return MySBuidler obj
		
		// 1. find strLogLen
		int strLogLen;
		strLogLen = str.length;
		
		// 2. find dataLogLen and dataPhysLen
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		int newLogLen = dataLogLen;
		// 3. if index is valid - find newLogLen, copy data after offset, check for resize, insert new chars, copy in old data after offset
		if (offset >= 0 && offset <= dataLogLen) {
			//find newLogLen
			newLogLen = strLogLen + dataLogLen;
			//copy in data after offset index
			char[] temp = new char[dataLogLen];
			int j = 0;
			for (int i = offset; i < dataLogLen; i++) {
				temp[j] = data[i];
				j++;
			}
			//check for resize
			if (newLogLen > dataPhysLen) {
				int newCap = 2 * newLogLen;
				resize(newCap);
			}
			//insert chars
			int k = 0;
			for (int i = offset; i < (offset + strLogLen); i++) {
				data[i] = str[k];
				k++;
			}
			//copy in rest of data
			int l = 0;
			for (int i = (offset + strLogLen); i < newLogLen; i++) {
				data[i] = temp[l];
				l++;
			}
		}
		
		// 4. update this.len with newLogLen
		this.len = newLogLen;
		
		// 5. return MySBuidler obj
		return this;
		
		//INSTRUCTIONS
		// Same as above but with array of char argument
	}
	
	//INSERT #3 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder insert(int offset, char c)
	{
		//LOGIC
		// 1. find dataLogLen and dataPhysLen
		// 2. if index is valid - find newLogLen, copy data after offset, check for resize, insert new char, copy in old data after offset
		// 3. update this.len with newLogLen
		// 4. return MySBuidler obj
		
		// 1. find dataLogLen and dataPhysLen
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		int newLogLen = dataLogLen;
		// 2. if index is valid - find newLogLen, copy data after offset, check for resize, insert new char, copy in old data after offset
		if (offset >= 0 && offset <= dataLogLen) {
			//find newLogLen
			newLogLen = 1 + dataLogLen;
			//copy in data after offset index
			char[] temp = new char[dataLogLen];
			int j = 0;
			for (int i = offset; i < dataLogLen; i++) {
				temp[j] = data[i];
				j++;
			}
			//check for resize
			if (newLogLen > dataPhysLen) {
				int newCap = 2 * newLogLen;
				resize(newCap);
			}
			//insert char
			data[offset] = c;
			//copy in rest of data
			int l = 0;
			for (int i = (offset + 1); i < newLogLen; i++) {
				data[i] = temp[l];
				l++;
			}
		}
		
		// 3. update this.len with newLogLen
		this.len = newLogLen;
		
		// 4. return MySBuidler obj
		return this;
		
		//INSTRUCTIONS
		// Same as above but with char argument
	}
	
	//INSERT #4 - DONE ---------------------------------------------------------------------------------------------
	public MySBuilder insert(int offset, MySBuilder S)
	{
		//LOGIC
		// 1. find SLogLen
		// 2. find dataLogLen and dataPhysLen
		// 3. if index is valid - find newLogLen, copy data after offset, check for resize, insert new chars, copy in old data after offset
		// 4. update this.len with newLogLen
		// 5. return MySBuidler obj
		
		// 1. find SLogLen
		int SLogLen;
		SLogLen = S.len;
		
		// 2. find dataLogLen and dataPhysLen
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		int newLogLen = dataLogLen;
		// 3. if index is valid - find newLogLen, copy data after offset, check for resize, insert new chars, copy in old data after offset
		if (offset >= 0 && offset <= dataLogLen) {
			//find newLogLen
			newLogLen = SLogLen + dataLogLen;
			//copy in data after offset index
			char[] temp = new char[dataLogLen];
			int j = 0;
			for (int i = offset; i < dataLogLen; i++) {
				temp[j] = data[i];
				j++;
			}
			//check for resize
			if (newLogLen > dataPhysLen) {
				int newCap = 2 * newLogLen;
				resize(newCap);
			}
			//insert chars
			int k = 0;
			for (int i = offset; i < (offset + SLogLen); i++) {
				data[i] = S.data[k];
				k++;
			}
			//copy in rest of data
			int l = 0;
			for (int i = (offset + SLogLen); i < newLogLen; i++) {
				data[i] = temp[l];
				l++;
			}
		}
		
		// 4. update this.len with newLogLen
		this.len = newLogLen;
		
		// 5. return MySBuidler obj
		return this;
		
		//INSTRUCTIONS
		// Same as above but with MySBuilder argument
	}

	//REPLACE ---------------------------------------------------------------------------------------------
	public MySBuilder replace(int start, int end, String str)
	{
		//LOGIC
		// 1. find strLogLen and range
		// 2. find dataLogLen and dataPhysLen
		// 3. if range is valid, insert substring, update this.len
		// 4. return MyStringBuilder obj
		
		// 1. find strLogLen and range
		int strLogLen = str.length();
		int range = end - start;
		
		// 2. find dataLogLen and dataPhysLen
		int dataPhysLen, dataLogLen;
		dataPhysLen = this.data.length;
		dataLogLen = this.len;
		
		if (start >= 0 && start < dataLogLen && end > 0 && end < dataLogLen && strLogLen == range) {
			int j = 0;
			for (int i = start; i < strLogLen; i++) {
				data[i] = str.charAt(j);
				j++;
			}
			//len doesnt change
		}
		
		if (start >= 0 && start < dataLogLen && end > 0 && end < dataLogLen && (strLogLen < range || strLogLen > range)) {
			//copy data chars from [0, start) into tempB char array
			char[] tempB = new char[start + 1];
			int counter1 = 0;
			for (int i = 0; i < start; i++) {
				tempB[i] = data[i];
				counter1++;
			}
			//copy data chars from [end, dataLogLen) into tempE char array
			char[] tempE = new char[dataLogLen];
			int j = 0;
			int counter2 = 0;
			for (int i = end; i < dataLogLen; i++) {
				tempE[j] = data[i];
				j++;
				counter2++;
			}
			//fill data array with tempB, str, and tempE
			char[] newData = new char[dataPhysLen];
			int newLogLen = counter1 + counter2 + strLogLen;
			for (int i = 0; i < counter1; i++) {
				newData[i] = tempB[i];
			}
			for (int i = 0; i < strLogLen; i++) {
				newData[i + start] = str.charAt(i);
			}
			for (int i = 0; i < counter2; i++) {
				newData[i + counter1 + strLogLen] = tempE[i];
			}
			this.data = newData;
			//update this.Len with newLogLen
			this.len = newLogLen;
		}
		
		// 4. return MyStringBuilder obj
		return this;
		
		//INSTRUCTIONS
		// Remove the characters from start (inclusive) to end (exclusive)
		// and insert str starting at start.  Do not call delete followed
		// by insert, since this will require shifting twice within the 
		// array and is very inefficient.  You should shift only one time
		// (the direction depends on the relative lengths of the substring
		// removed vs the string inserted).  Note that if the lengths of
		// the removed and inserted strings are the same, you should not
		// shift at all.
		// return this
	}
	
	//SUBSTRING - DONE ---------------------------------------------------------------------------------------------
	public String substring(int start, int end)
	{
		//LOGIC
		// 1. if range is invalid, return null
		// 2. if range is valid, create subStr char array, fill with chars from data, return string
		
		
		// 1. if range is invalid, return null
		if (start < 0 || end > this.len)
		{
			return null;
		}
		// 2. if range is valid, create subStr char array, fill with chars from data, return string
		else
		{
			//create substring array length of range
			char [] subStr = new char [end-start];
			//copy char data into subStr
			for (int i = 0; i < subStr.length; i++)
			{
				subStr[i] = data[start + i];
			}
			//convert subStr char array to String
			String subString = new String(subStr);
			//return subString
			return subString;
		}
		
		//INSTRUCTIONS
		// return a new String which is the substring of this MySBuilder
		// from start (inclusive) to end (exclusive).  If the range is 
		// invalid return null
	}
	
	//SPLIT - DONE ---------------------------------------------------------------------------------------------
	public String [] split(char delim)
	{
		//LOGIC
		// 1. create relevant vars
		// 2. create array of delim indicies
		// 3. get chars from beginning to first delim, convert to String, add to ArrayList
		// 4. get chars for delims other than first and last, convert to String, add to ArrayList
		// 5. get chars from last delim to end, convert to String, add to ArrayList
		// 6. create String array from ArrayList and return		
		
		// 1. create relevant vars
		String[] stringArray = new String[4];
		ArrayList<String> returnList = new ArrayList<String>();
		int[] delimArr = new int[data.length];
		int count = 0;
		String t; 
		
		// 2. create array of delim indicies 
		for (int i = 0; i < this.len; i++) {
			//if data[i] is a delim and not first or last index, add index to array of delim indicies
			if (data[i] == delim && i != 0 && i != this.len-1) { 
				if (data[i] != data[i - 1]) { 
					delimArr[count]=i;
					count++;
				}					
			}
		}
		
		// 3. get chars from beginning to first delim, convert to String, add to ArrayList
		char[] temp = new char[data.length];
		int counter = 0;
		for (int j = 0; j < delimArr[0]; j++) {
			if (data[j] != delim) {
				temp[counter] = data[j];
				counter++;						
			}
		}
		//create string and append to ArrayList
		t = new String(temp);
		returnList.add(t);
		
		// 4. get chars for delims other than first and last, convert to String, add to ArrayList
		for (int i = 0; i < count - 1; i++) {
			counter = 0;
			temp = new char[data.length];
			for (int j = delimArr[i]; j < delimArr[i + 1]; j++) {
				//copy chars into temp
				if (data[j] != delim) {
					temp[counter] = data[j];
					counter++;
				}			
			}
		//create string and append to ArrayList
		t = new String(temp);
		returnList.add(t);
		}		
		
		// 5. get chars from last delim to end, convert to String, add to ArrayList
		counter=0;
		temp = new char[data.length];
		for (int j = delimArr[count - 1]; j < this.len; j++) {	
			//copy data into temp
			if (data[j] != delim) {
				temp[counter] = data[j];
				counter++;						
			}			
		}
		//create string and append to ArrayList
		t = new String(temp);
		returnList.add(t);
	
		// 6. create String array from ArrayList and return
		String[] returnArr = new String[returnList.size()];
		returnArr = returnList.toArray(returnArr);
		return returnArr;
		
		//INSTRUCTIONS
		// Simplified split method returns array of String which the delim
		// character will divide the MySBuilder into.  Be careful of special
		// cases where delim occurs in consecutive locations or at the front
		// or end of the MySBuilder.  In these cases the extra delim characters
		// should be ignored.  See examples in SBuilderTest.java.  You MAY NOT
		// use any predefined split() method or variant thereof to do this method.
		// It must be done from scratch on the underlying array of characters.
		// You may use an ArrayList<String> to store your temporary results, and
		// the ArrayList method toArray(T [] a) method to return them.  See the
		// ArrayList API for details.
	}
}
	