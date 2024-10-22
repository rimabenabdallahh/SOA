package graphQl;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entite.Logement;
import entite.RendezVous;
import repository.RendezVousRepository;

public class Mutation implements GraphQLRootResolver {
    private final RendezVousRepository rendezVousRepository;

    public Mutation(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    public RendezVous createRendezVous(int id, String date, String heure, int refLog, String numTel) {
        Logement logement = rendezVousRepository.logementMetier.getLogementsByReference(refLog);
        if (logement != null) {
            RendezVous nouveauRdv = new RendezVous(id, date, heure, logement, numTel);
            rendezVousRepository.addRendezVous(nouveauRdv);
            return nouveauRdv;
        }
        return null;
    }

    public boolean updateRendezVous(int id, String date, String heure, String numTel) {
        RendezVous rendezVous = new RendezVous(id, date, heure, rendezVousRepository.getLogementByRDV(id), numTel);
        return rendezVousRepository.updateRendezVous(rendezVous);
    }

    public boolean deleteRendezVous(int id) {
        return rendezVousRepository.deleteRendezVous(id);
    }

}
