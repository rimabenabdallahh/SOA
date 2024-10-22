package graphQl;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import repository.LogementRepository;
import repository.RendezVousRepository;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
    public GraphQLEndpoint(){
        super(buildSchema());
    }
    private static GraphQLSchema buildSchema() {
        RendezVousRepository rdvRepo = new RendezVousRepository();

        return SchemaParser.newParser()
                .file("schema.graphql")
                .resolvers(new Querries(rdvRepo), new Mutation(rdvRepo))
                .build()
                .makeExecutableSchema();
    }

}