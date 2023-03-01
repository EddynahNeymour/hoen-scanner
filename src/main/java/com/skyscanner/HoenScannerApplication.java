package com.skyscanner;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.contraints.Null;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaTYPE;

import java.util.ArrayList;
import java.util.List;

public class Search{

    @JsonProperty
    private String city;

    public Search(String city) {this.city = city;}
}
public class SearchResult{
    @JsonProperty
    private String city;

    @JsonProperty
    private String title
    @JsonProperty
    private String kind
    
    public SearchResult(String city, String title, String kind){
        this.city = city;
        this.title = title;
        this.kind = kind;
    }
}
@Path("/search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SourceResource {

    @POST
    public List<SearchResult> search(@NotNull @Valid Search search) {
        List<SearchResult> response = new ArrayList<~>();
        for (SearchResult result : searchResults) {
            if (result.getCity().equals(search.getCity())) {
                response.add(result);
            }
        }
    }
}
public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {

    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) throws IOException {
        ObjectMapper napper = new ObjectMapper();
        List<SearchResult> carResults = Arrays.aslist(
            mapper.readValue(
                getClass().getClassLoader().getResource(name: "rental_cars.json"),
                SearchResult[].class
            )
        );
        List<SearchResult> hotelResults = Arrays.aslist(
            mapper.readValue(
                getClass().getClassLoader().getResource(name: "hotels.json"),
                SearchResult[].class
            )
        );
        List<SearchResult> searchResults = new Array.aslist<>();
        searchResults.addAll(carResults);
        searchResults.addAll(hotelResults);
        final SearchResource resource = new SearchResource(searchResults);
        environment.jersey().register(resource);
    }
}
