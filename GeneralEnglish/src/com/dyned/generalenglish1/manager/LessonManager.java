package com.dyned.generalenglish1.manager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.dyned.generalenglish1.activity.ComprehensionQuestionAudioTextActivity;
import com.dyned.generalenglish1.activity.ComprehensionQuestionTextActivity;
import com.dyned.generalenglish1.activity.ComprehentionResultStatusActivity;
import com.dyned.generalenglish1.activity.GrammarQuestionTextActivity;
import com.dyned.generalenglish1.activity.GrammarResultStatusActivity;
import com.dyned.generalenglish1.activity.GrammarSentenceBuilderActivity;
import com.dyned.generalenglish1.activity.ListeningActivity;
import com.dyned.generalenglish1.app.GEApplication;
import com.dyned.generalenglish1.model.GEAnswerPacket;
import com.dyned.generalenglish1.model.GEComprehension;
import com.dyned.generalenglish1.model.GEGrammar;
import com.dyned.generalenglish1.model.GELesson;
import com.dyned.generalenglish1.model.GEMainMenu;
import com.dyned.generalenglish1.model.SerializedNameValuePair;

public class LessonManager {

	private static LessonManager instance;

	private static ArrayList<Integer> listOpenedIds;
	
	private List<Activity> listComprehensionPage;
	private List<Activity> listGrammarPage;
	private Activity comprehensionResultPage;
	
	private GEMainMenu currentUnit;
	private GELesson currentLesson;
	
	private GEComprehension currentComprehension;
	private GEGrammar currentGrammar;
	private List<SerializedNameValuePair> currentComprehensionAnswers;
	private List<SerializedNameValuePair> currentGrammarAnswers;
	
	private int currentComprehentionIndex;
	private int currentGrammarIndex;
	
	private LessonManager(){}
	
	public static LessonManager getInstance(){
		if (instance == null) {
			instance = new LessonManager();
		}
		return instance;
	}
	
	public GEMainMenu getCurrentUnit(){
		return currentUnit;
	}
	
	public GELesson getCurrentLesson(){
		return currentLesson;
	}
	
	public List<SerializedNameValuePair> getCurrentComprehensionAnswer(){
		return currentComprehensionAnswers;
	}
	
	public List<SerializedNameValuePair> getCurrentGrammarAnswer(){
		return currentGrammarAnswers;
	}
	
	private void init(){
		currentUnit = null;
		currentLesson = null;
		currentComprehension = null;
		currentGrammar = null;
		currentComprehentionIndex = 0;
		currentGrammarIndex = 0;
		currentComprehensionAnswers = new ArrayList<SerializedNameValuePair>();
		currentGrammarAnswers = new ArrayList<SerializedNameValuePair>();
		listComprehensionPage = new ArrayList<Activity>();
		listGrammarPage = new ArrayList<Activity>();
	}
	
	public void startLesson(Context context, GEMainMenu unit, GELesson lesson){
		init();
		
		currentUnit = unit;
		currentLesson = lesson;
		
		startListening(context);
	}

	private void startListening(Context context) {
		Intent i = new Intent(context, ListeningActivity.class);
		i.putExtra("GElesson", currentLesson);
		context.startActivity(i);
	}
	
	public void doneListening(Activity page) {
		listComprehensionPage.add(page);
		startComprehension(page);
	}

	private void startComprehension(Context context) {
		if (currentComprehension == null) currentComprehension = currentLesson.getComprehension();
		if (currentComprehensionAnswers == null) currentComprehensionAnswers = new ArrayList<SerializedNameValuePair>();
			
		String comprehensionType = currentComprehension.getType();
		
		if (comprehensionType.equals("textonly")) {
			//start activity questiontextactivity
			boolean lastQuestion = (currentComprehentionIndex == (currentComprehension.getListQuestion().size() - 1));
			
			Intent i = new Intent(context, ComprehensionQuestionTextActivity.class);
			i.putExtra("GEquestion", currentLesson.getComprehension().getListQuestion().get(currentComprehentionIndex));
			i.putExtra("lastQuestion", lastQuestion);
			context.startActivity(i);
			
		} else if (comprehensionType.equals("audioandtext")){
			//start activity questionaudiotextactivity
			boolean lastQuestion = (currentComprehentionIndex == (currentComprehension.getListQuestion().size() - 1));
			
			Intent i = new Intent(context, ComprehensionQuestionAudioTextActivity.class);
			i.putExtra("GEquestion", currentLesson.getComprehension().getListQuestion().get(currentComprehentionIndex));
			i.putExtra("lastQuestion", lastQuestion);
			context.startActivity(i);
		}
	}
	
	public void doneAnswerComprehension(Activity page, SerializedNameValuePair answer){
		currentComprehentionIndex++;
		currentComprehensionAnswers.add(answer);
		listComprehensionPage.add(page);
		startComprehension(page);
	}
	
	public void doneComprehension(Activity page, SerializedNameValuePair answer){
		currentComprehensionAnswers.add(answer);
		listComprehensionPage.add(page);
		
		//add answer packet history
		addComprehensionAnswerPacket(page);
		
		Intent in = new Intent(page, ComprehentionResultStatusActivity.class);
		in.putExtra("isPass", isAllAnswerCorrect(getCurrentComprehensionAnswer()));
		in.putExtra("type", currentComprehension.getType());
		page.startActivity(in);
	}

	private void addComprehensionAnswerPacket(Activity c) {
		GEAnswerPacket packet = new GEAnswerPacket();
		packet.setCoversation(GEApplication.app);
		packet.setUnit(getCurrentUnit().getCode());
		packet.setLesson(getCurrentLesson().getCode());
		packet.setCompletedTime(System.currentTimeMillis() / 1000);
		packet.setComprehentionAttempted(currentComprehensionAnswers.size());
		packet.setComprehentionCorrect(checkTotalCorrectAnswers(currentComprehensionAnswers));
		
		UserPreference.getInstance(c).addToCurrentAnswerPacket(packet);
	}
	
	private void addGrammarAnswerPacket(Activity c) {
		GEAnswerPacket packet = new GEAnswerPacket();
		packet.setCoversation(GEApplication.app);
		packet.setUnit(getCurrentUnit().getCode());
		packet.setLesson(getCurrentLesson().getCode());
		packet.setCompletedTime(System.currentTimeMillis() / 1000);
		packet.setGrammarAttempted(currentGrammarAnswers.size());
		packet.setGrammarCorrect(checkTotalCorrectAnswers(currentGrammarAnswers));
		
		UserPreference.getInstance(c).addToCurrentAnswerPacket(packet);
	}

	public static boolean checkAnswers(List<SerializedNameValuePair> answers) {
		for (SerializedNameValuePair answer : answers) {
			if (!answer.getName().equals(answer.getValue())) {
				return false;
			}
		}
		return true;
	}
	
	public static int checkTotalCorrectAnswers(List<SerializedNameValuePair> answers) {
		int correct = 0;
		for (SerializedNameValuePair answer : answers) {
			if (answer.getName().equals(answer.getValue())) {
				correct++;
			}
		}
		return correct;
	}

	public void backComprehension() {
		if(currentComprehentionIndex > 0) currentComprehentionIndex--;
		
		if(currentComprehensionAnswers.size() > 0) currentComprehensionAnswers.remove(currentComprehensionAnswers.size() - 1);
		
		if(listComprehensionPage.size() > 0) listComprehensionPage.remove(listComprehensionPage.size() - 1);
	}

	public void repeatComprehension(Context context) {
		currentComprehentionIndex = 0;
		currentComprehensionAnswers.clear();
				
		closeAllComprehension();
		
		startListening(context);
	}

	public void prepareGrammar(Activity page){
		if (listGrammarPage == null) {
			listGrammarPage = new ArrayList<Activity>();
		} else {
			listGrammarPage.clear();
		}
				
		
		startGrammar(page);
		comprehensionResultPage = page;
	}
	
	public void startGrammar(Activity page) {
		if (currentGrammar == null) currentGrammar = currentLesson.getGrammar();
		if (currentGrammarAnswers == null) currentGrammarAnswers = new ArrayList<SerializedNameValuePair>();
		
		String grammarType = currentGrammar.getType();
		
		if (grammarType.equals("textonly")) {
			//start activity questiontextactivity
			boolean lastQuestion = (currentGrammarIndex == (currentGrammar.getListQuestion().size() - 1));
			
			Intent i = new Intent(page, GrammarQuestionTextActivity.class);
			i.putExtra("GEquestion", currentLesson.getGrammar().getListQuestion().get(currentGrammarIndex));
			i.putExtra("lastQuestion", lastQuestion);
			page.startActivity(i);
			
		} else if (grammarType.equals("sentencebuilder")){
			//start activity questionbuilderactivity
			boolean lastQuestion = (currentGrammarIndex == (currentGrammar.getListQuestion().size() - 1));
			
			Intent i = new Intent(page, GrammarSentenceBuilderActivity.class);
			i.putExtra("GEquestion", currentLesson.getGrammar().getListQuestion().get(currentGrammarIndex));
			i.putExtra("lastQuestion", lastQuestion);
			page.startActivity(i);
		}
	}

	public void doneGrammar(Activity page, SerializedNameValuePair answer) {
		currentGrammarAnswers.add(answer);
		listGrammarPage.add(page);
		
		//add answer packet history
		addGrammarAnswerPacket(page);
		
		Intent in = new Intent(page, GrammarResultStatusActivity.class);
		in.putExtra("isPass", isAllAnswerCorrect(getCurrentGrammarAnswer()));
		page.startActivity(in);
	}
	
	public void doneAnswerGrammar(Activity page, SerializedNameValuePair answer) {
		currentGrammarIndex++;
		currentGrammarAnswers.add(answer);
		listGrammarPage.add(page);
		startGrammar(page);
	}
	
	public void backGrammar() {
		if(currentGrammarIndex > 0) currentGrammarIndex--;
		
		if(currentGrammarAnswers.size() > 0) currentGrammarAnswers.remove(currentGrammarAnswers.size() - 1);
		
		if(listGrammarPage.size() > 0) listGrammarPage.remove(listGrammarPage.size() - 1);
	}

	public void repeatGrammar(Activity page) {
		currentGrammarIndex = 0;
		currentGrammarAnswers.clear();
				
		closeAllGrammar();
		
		startGrammar(page);
	}
	
	private void closeAllGrammar(){
		for (int i = listGrammarPage.size() - 1; i >= 0; i--) {
			listGrammarPage.get(i).finish();
		}
		
		listGrammarPage.clear();
	}
	
	private void closeAllComprehension(){
		for (int i = listComprehensionPage.size() - 1; i >= 0; i--) {
			listComprehensionPage.get(i).finish();
		}
		
		listComprehensionPage.clear();
	}
	
	public void finishLesson(){		
		closeAllGrammar();
		if(comprehensionResultPage != null) comprehensionResultPage.finish();
		closeAllComprehension();
	}
	
	public static boolean isAllAnswerCorrect(List<SerializedNameValuePair> answers){
		for (int i = 0; i < answers.size(); i++) {
			if (!answers.get(i).getName().equals(answers.get(i).getValue())) {
				return false;
			}
		}
		return true;
	}
	
	public void setOpenedUnits(List<Integer> openedIds){
		listOpenedIds = new ArrayList<Integer>(openedIds);
	}
	
	public List<Integer> getOpenedUnits(){
		if (listOpenedIds == null) {
			listOpenedIds = new ArrayList<Integer>();
		}
		return listOpenedIds;
	}
	
}
