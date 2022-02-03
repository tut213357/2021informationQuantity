package s4.B213357; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
}                        
*/


public class TestCase {
    static boolean success = true;

    public static void main(String[] args) {
		try {
			FrequencerInterface  myObject;
			int freq;
			System.out.println("checking Frequencer");

			// This is smoke test
			myObject = new Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("H".getBytes());
			freq = myObject.frequency();
			assert freq == 4: "Hi Ho Hi Ho, H: " + freq;

			myObject = new Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("Hi".getBytes());
			freq = myObject.frequency();
			assert freq == 2: "Hi Ho Hi Ho, Hi: " + freq;

			myObject = new Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("Ho ".getBytes());
			freq = myObject.frequency();
			assert freq == 1: "Hi Ho Hi Ho, Ho : " + freq;

			myObject = new Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("Hi Ho ".getBytes());
			freq = myObject.frequency();
			assert freq == 1: "Hi Ho Hi Ho, Hi Ho : " + freq;

			// Write your testCase here
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget(" ".getBytes());
			freq = myObject.frequency();
			assert freq == 3: "Hi Ho Hi Ho,  : " + freq;

			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("".getBytes());
			freq = myObject.frequency();
			assert freq == 11: "Hi Ho Hi Ho, : " + freq;

			myObject.setSpace("H".getBytes()); // 検索文字列が検索対象文字列より長い場合
			myObject.setTarget("HH".getBytes());
			freq = myObject.frequency();
			assert freq == 0: "H, HH: " + freq;

			myObject.setSpace("".getBytes()); // 検索対象文字列が空の場合
			myObject.setTarget("H".getBytes());
			freq = myObject.frequency();
			assert freq == 0: ", H: " + freq;

			myObject.setSpace("ひ ほ ひ ほ".getBytes()); // 日本語(全角文字列)の場合
			myObject.setTarget("ひ".getBytes());
			freq = myObject.frequency();
			assert freq == 2: "ひ ほ ひ ほ, ひ: " + freq;
		}
		catch(Exception e) {
			System.out.println("Exception occurred in Frequencer Object");
			success = false;
		}

		try {
			InformationEstimatorInterface myObject;
			double value;

			System.out.println("checking InformationEstimator");

			myObject = new InformationEstimator();

			myObject.setSpace("3210321001230123".getBytes());
			myObject.setTarget("0".getBytes());
			value = myObject.estimation();
			assert (value > 1.9999) && (2.0001 >value): "IQ for 0 in 3210321001230123 should be 2.0. But it returns "+value;
			
			myObject.setTarget("01".getBytes());
			value = myObject.estimation();
			assert (value > 2.9999) && (3.0001 >value): "IQ for 01 in 3210321001230123 should be 3.0. But it returns "+value;
			
			myObject.setTarget("0123".getBytes());
			value = myObject.estimation();
			assert (value > 2.9999) && (3.0001 >value): "IQ for 0123 in 3210321001230123 should be 3.0. But it returns "+value;
			
			myObject.setTarget("00".getBytes());
			value = myObject.estimation();
			assert (value > 3.9999) && (4.0001 >value): "IQ for 00 in 3210321001230123 should be 3.0. But it returns "+value;
			
			myObject.setTarget("".getBytes());
			value = myObject.estimation();
			assert (value == 0.0): "IQ for  in 3210321001230123 should be 0.0. But it returns "+value;

			myObject.setTarget(null);
			value = myObject.estimation();
			assert (value == 0.0): "IQ for 00 in null should be 0. But it returns "+value;

			myObject.setSpace("".getBytes());
			myObject.setTarget("00".getBytes());
			value = myObject.estimation();
			assert (value == Double.MAX_VALUE): "IQ for 00 in should be Double.MAX_VALUE. But it returns "+value;

			myObject.setSpace(null);
			myObject.setTarget("00".getBytes());
			value = myObject.estimation();
			assert (value == Double.MAX_VALUE): "IQ for 00 in null should be Double.MAX_VALUE. But it returns "+value;
			
			// 処理前の時刻を取得
			/*
			long startTime = System.nanoTime();
			myObject.setSpace("321032100123012332103210012301233210321001230123".getBytes());
			for (int i = 0; i < 100; i++) {
				myObject.setTarget("321032100123012332103210012301233210321001230123".getBytes());
				value = myObject.estimation();
				// assert (value > 3.9999) && (4.0001 >value): "IQ for 00 in 3210321001230123 should be 3.0. But it returns "+value;
			}
			// 処理後の時刻を取得
    		long endTime = System.nanoTime();
			System.out.println("開始時刻:" + startTime + " ナノ秒");
			System.out.println("終了時刻:" + endTime + " ナノ秒");
			System.out.println("処理時間:" + (endTime - startTime) + " ナノ秒");*/
		}
		catch(Exception e) {
			System.out.println("Exception occurred in InformationEstimator Object");
			success = false;
		}
        if(success) { System.out.println("TestCase OK"); } 
    }
}	    
	    
