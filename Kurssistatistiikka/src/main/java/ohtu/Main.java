package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        int yhteensaTehtavia = 0;
        int yhteensaTunteja = 0;
        System.out.println("opiskelijanumero " + studentNr);
        for (Submission submission : subs) {
            System.out.println(submission);
            yhteensaTehtavia += submission.montaTehtavaa();
            yhteensaTunteja += submission.getHours();
        }
        
        System.out.println("yhteensä: " + yhteensaTehtavia + " tehtavää ja "+ yhteensaTunteja + " tuntia");
       

    }
}