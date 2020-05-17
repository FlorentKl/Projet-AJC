package projetSpringBoot.wrapper;

import java.util.ArrayList;
import java.util.List;

import projetSpringBoot.model.recette.EtapeRecette;
import projetSpringBoot.model.recette.Recette;

public class RecetteAndEtapeWrapper {
    private Recette recette;
    private List<EtapeRecette> etapeRecettes = new ArrayList<EtapeRecette>();

    public RecetteAndEtapeWrapper() {
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public List<EtapeRecette> getEtapeRecettes() {
        return etapeRecettes;
    }

    public void setEtapeRecettes(List<EtapeRecette> etapeRecettes) {
        this.etapeRecettes = etapeRecettes;
    }


}
