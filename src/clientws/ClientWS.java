
package clientws;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;

public class ClientWS {

    
    public static void main(String[] args) throws URISyntaxException, IOException {
        //Créer un constructeur d'uri
        
        URIBuilder uriBuilder= new URIBuilder();
        
        //type de schémas
        uriBuilder.setScheme("https");
        
        
        
        //precisant notre host
        uriBuilder.setHost("maps.googleapis.com/");
        
        //le path le chemin vers ton url celui qui identifie de maniéer unique ton web service
        uriBuilder.setPath("/maps/api/timezone/json");
        
        //les paramétres 
        double latitude=45;
        double longitude=-73;
        String location=latitude+","+longitude;
        
        uriBuilder.addParameter("location", location);
        
       long time=1631558725;
       uriBuilder.addParameter("timestamp", String.valueOf(time));
       String key= "AIzaSyD7a_uULBq-ZHnWVyx8m52fK7Sa1CCAqko";
       uriBuilder.addParameter("key", key);
       
       
       URI uri= uriBuilder.build();
       
       //créer un Http requet un get à traver le uri
       HttpUriRequest requete=new HttpGet(uri);
        System.out.println(uriBuilder);
       //créer le client qui va utiliser cette requete
       HttpClientBuilder clientBuilder= HttpClientBuilder.create();
       
       //le client
       HttpClient client=clientBuilder.build();
       
       //le reponse 
       HttpResponse httpResponse=client.execute(requete);
       
        
        String reponse= EntityUtils.toString(httpResponse.getEntity());
        JSONObject main=JSONObject.fromObject(reponse);
        
        System.out.println(main.getInt("dstOffset"));
        System.out.println(main.getInt("rawOffset"));
        System.out.println(main.getString("status"));
        System.out.println(main.getString("timeZoneId"));
        System.out.println(main.getString("timeZoneName"));
        
        //System.out.println(reponse);
       
       
   
    }
    
}
