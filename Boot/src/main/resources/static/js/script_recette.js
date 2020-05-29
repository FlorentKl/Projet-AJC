var nombreEtapeRecette = 0;

function modifierTypeRecette(path) {
    let getType = document.querySelector("#type-recette");
    let classeRecette = getType.value;
    document.querySelector("#form-recette-create").action =
        path + "/creation-recette/ajout/" + classeRecette;

    document.querySelector("#hidden-version").name = classeRecette + ".version";

    document.querySelector("#label-nom").name = classeRecette + ".nom";
    document.querySelector("#input-nom").name = classeRecette + ".nom";

    document.querySelector("#label-cout").name = classeRecette + ".cout";
    document.querySelector("#select-cout").name = classeRecette + ".cout";

    document.querySelector("#label-difficulte").name =
        classeRecette + ".difficulte";
    document.querySelector("#select-difficulte").name =
        classeRecette + ".difficulte";

    document.querySelector("#label-parts").name = classeRecette + ".nbPersonne";
    document.querySelector("#select-parts").name =
        classeRecette + ".nbPersonne";
}

function ajoutClickEventEtapeRecette() {
    let button = document.querySelector("#btn-ajout-etape-recette");
    button.addEventListener("click", function (event) {
        ajoutEtapeRecette();
    });
}

function ajoutEtapeRecette() {
    let containerEtapeRecette = document.querySelector("#etape-recette");

    let newEtape = document.createElement("div");
    newEtape.id = "etape-" + nombreEtapeRecette;
    newEtape.className = "form-group border border-dark";

    let versionHidden = document.createElement("input");
    versionHidden.id = "etapeRecettes[" + nombreEtapeRecette + "].version";
    versionHidden.name = "etapeRecettes[" + nombreEtapeRecette + "].version";
    versionHidden.type = "hidden";
    versionHidden.value = "";
    newEtape.append(versionHidden);

    let labelEtape = document.createElement("label");
    labelEtape.innerHTML = "Nouvelle Etape : ";
    newEtape.append(labelEtape);

    let numEtapeHiddenInput = document.createElement("input");
    numEtapeHiddenInput.id =
        "etapeRecettes[" + nombreEtapeRecette + "].numEtape";
    numEtapeHiddenInput.name =
        "etapeRecettes[" + nombreEtapeRecette + "].numEtape";
    numEtapeHiddenInput.type = "hidden";
    numEtapeHiddenInput.value = nombreEtapeRecette;
    newEtape.append(numEtapeHiddenInput);

    let buttonSupprimer = document.createElement("button");
    buttonSupprimer.className = "btn btn-danger";
    buttonSupprimer.addEventListener("click", function (event) {
        containerEtapeRecette.removeChild(newEtape);
    });
    buttonSupprimer.innerHTML = "-";
    newEtape.append(buttonSupprimer);

    let newTextArea = document.createElement("textarea");
    newTextArea.className = "form-control";
    newTextArea.id = "etapeRecettes[" + nombreEtapeRecette + "].texte";
    newTextArea.name = "etapeRecettes[" + nombreEtapeRecette + "].texte";
    newTextArea.rows = 5;
    newTextArea.cols = 50;
    newEtape.append(newTextArea);

    containerEtapeRecette.append(newEtape);

    nombreEtapeRecette++;
}
