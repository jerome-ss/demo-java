package com.jerome.arithmetic;

import java.util.*;

/**
 * Tries算法
 *
 * @author jerome
 * @date 2016/9/1 9:26
 */
public class TrieTest {

	private Node note = new Node();

	/**
	 * 内部类
	 *
	 * @author jerome
	 */
	protected class Node {

		/** 单词个数 */
		protected int words;
		/** 字符前缀的个数 */
		protected int prefixes;
		/** 子节点 */
		protected HashMap<Integer, Node> childNode;

		Node() {
			this.words = 0;
			this.prefixes = 0;
			childNode = new HashMap<>();
		}
	}

	/**
	 * 获取tire树中所有的词
	 *
	 * @return
	 */
	public List<String> listAllWords() {

		List<String> words = new ArrayList<>();
		HashMap<Integer, Node> edges = note.childNode;

		for (Iterator iterator = edges.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<Integer, Node> entry = (Map.Entry<Integer, Node>) iterator.next();
			String word = "" + (char) ('a' + entry.getKey());
			depthFirstSearchWords(words, entry.getValue(), word);
		}

		return words;
	}

	/**
	 * @param words
	 * @param vertex
	 * @param wordSegment
	 */
	private void depthFirstSearchWords(List words, Node vertex, String wordSegment) {
		if (vertex.words != 0) {
			words.add(wordSegment);
		}
		for (Iterator iterator = vertex.childNode.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<Integer, Node> entry = (Map.Entry<Integer, Node>) iterator.next();
			String word = "" + (char) ('a' + entry.getKey());
			depthFirstSearchWords(words, entry.getValue(), word);
		}
	}

	/**
	 * 计算指定前缀单词的个数
	 *
	 * @param prefix
	 * @return
	 */
	public int countPrefixes(String prefix) {
		return countPrefixes(note, prefix);
	}

	private int countPrefixes(Node vertex, String prefixSegment) {
		if (prefixSegment.length() == 0) { // reach the last character of the
			// word
			return vertex.prefixes;
		}

		char c = prefixSegment.charAt(0);
		Integer index = c - 'a';
		if (!vertex.childNode.containsKey(index)) { // the word does NOT exist
			return 0;
		} else {
			return countPrefixes(vertex.childNode.get(index), prefixSegment.substring(1));

		}

	}

	/**
	 * 计算完全匹配单词的个数
	 *
	 * @param word
	 * @return
	 */
	public int countWords(String word) {
		return countWords(note, word);
	}

	private int countWords(Node vertex, String wordSegment) {
		if (wordSegment.length() == 0) { // reach the last character of the word
			return vertex.words;
		}

		char c = wordSegment.charAt(0);
		int index = c - 'a';
		if (!vertex.childNode.containsKey(index)) { // the word does NOT exist
			return 0;
		} else {
			return countWords(vertex.childNode.get(index), wordSegment.substring(1));

		}

	}

	/**
	 * 向tire树添加一个词
	 *
	 * @param word
	 */

	public void addWord(String word) {
		addWord(note, word);
	}

	/**
	 * 根据上级节点添加内容
	 *
	 * @param node
	 *            上级节点
	 * @param word
	 *            添加的内容
	 */
	private void addWord(Node node, String word) {

		if (word.length() == 0) {
			// 所有字符都添加完了
			node.words++;
		} else {
			node.prefixes++;
			char c = word.charAt(0);
			c = Character.toLowerCase(c);
			int index = c - 'a';
			if (!node.childNode.containsKey(index)) {
				node.childNode.put(index, new Node());
			}

			addWord(node.childNode.get(index), word.substring(1));
		}
	}

	public String replaceAll(String str) {
		char[] w = str.toCharArray();
		Node vertex = note;
		String ret = "";
		String s = "";
		for (int i = 0; i < w.length; i++) {
			char c = w[i];
			c = Character.toLowerCase(c);
			int index = c - 'a';
			if (!vertex.childNode.containsKey(index)) {// 如果没有子节点
				if (vertex.words != 0)// 如果是一个单词，则返回
				{
					ret += "*" + c;
					return ret + replaceAll(str.substring(i + 1));
				} else {
					ret += s + c;
					return ret + replaceAll(str.substring(i + 1));
				}
			} else {
				if (vertex.words != 0)
					ret += s;
				s += c;
				vertex = vertex.childNode.get(index);
			}
		}
		return "*";
	}

	/**
	 * 返回指定字段前缀匹配最长的单词。
	 *
	 * @param word
	 * @return
	 */
	public String getMaxMatchWord(String word) {
		String s = "";
		String temp = "";// 记录最近一次匹配最长的单词
		char[] w = word.toCharArray();
		Node vertex = note;
		for (int i = 0; i < w.length; i++) {
			char c = w[i];
			c = Character.toLowerCase(c);
			int index = c - 'a';
			if (!vertex.childNode.containsKey(index)) {// 如果没有子节点
				if (vertex.words != 0)// 如果是一个单词，则返回
					return s;
				else
					// 如果不是一个单词则返回null
					return null;
			} else {
				if (vertex.words != 0)
					temp = s;
				s += c;
				vertex = vertex.childNode.get(index);
			}
		}
		// trie中存在比指定单词更长（包含指定词）的单词
		if (vertex.words == 0)//
			return temp;
		return s;
	}

	public static void main(String args[]) {
		TrieTest trieTest = new TrieTest();
		trieTest.addWord("擦");
		trieTest.addWord("靠");
		trieTest.addWord("江泽民");

		String ret = trieTest.replaceAll("我擦，江泽民好帅");
		System.out.println(ret);
	}
}