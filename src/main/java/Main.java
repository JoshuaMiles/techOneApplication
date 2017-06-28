import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by josh on 26/6/17.
 */



public class Main {
  public static void main(String[] args){

      port(1337);
      Map<String, String> maps;
      maps = new HashMap<String, String>();

      /**
       * The starting path where the user can input the given number
       */
      get("/", (req,res) -> {
          return new ModelAndView(maps,"NumberToWord.hbs");

      }, new HandlebarsTemplateEngine());


      /**
       * Resets the input so that the query can be performed again
       */
      get("/reset", (req,res) -> {

          maps.clear(); // Clearing the map so that it can have more input

          return new ModelAndView(maps,"NumberToWord.hbs");

      }, new HandlebarsTemplateEngine());

      /**
       * The path which queries the server and returns the model and view of the result
       */
      get("/api/numberToWordConverter/:number", (req,res) -> {

              NumberToWord numberToWordObj = new NumberToWord(req.params(":number"));

              maps.put("message",numberToWordObj.wordify());
              return new ModelAndView(maps,"NumberToWord.hbs");

      }, new HandlebarsTemplateEngine());

    }

}
