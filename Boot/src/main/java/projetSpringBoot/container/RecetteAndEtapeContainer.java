package projetSpringBoot.container;

import java.util.ArrayList;
import java.util.List;

import projetSpringBoot.model.recette.Boisson;
import projetSpringBoot.model.recette.Dessert;
import projetSpringBoot.model.recette.Entree;
import projetSpringBoot.model.recette.EtapeRecette;
import projetSpringBoot.model.recette.Plat;

public class RecetteAndEtapeContainer {
    private Entree entree;
    private Plat plat;
    private Dessert dessert;
    private Boisson boisson;
    private List<EtapeRecette> etapeRecettes = new ArrayList<EtapeRecette>();

    public RecetteAndEtapeContainer() {
    }

    /**
     * @return the entree
     */
    public Entree getEntree() {
        return entree;
    }

    /**
     * @param entree the entree to set
     */
    public void setEntree(Entree entree) {
        this.entree = entree;
    }

    /**
     * @return the plat
     */
    public Plat getPlat() {
        return plat;
    }

    /**
     * @param plat the plat to set
     */
    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    /**
     * @return the dessert
     */
    public Dessert getDessert() {
        return dessert;
    }

    /**
     * @param dessert the dessert to set
     */
    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    /**
     * @return the boisson
     */
    public Boisson getBoisson() {
        return boisson;
    }

    /**
     * @param boisson the boisson to set
     */
    public void setBoisson(Boisson boisson) {
        this.boisson = boisson;
    }

    public List<EtapeRecette> getEtapeRecettes() {
        return etapeRecettes;
    }

    public void setEtapeRecettes(List<EtapeRecette> etapeRecettes) {
        this.etapeRecettes = etapeRecettes;
    }

}
