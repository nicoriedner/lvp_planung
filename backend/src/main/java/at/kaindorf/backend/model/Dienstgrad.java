package at.kaindorf.backend.model;

public enum Dienstgrad {
    PROBEFEUERWEHRMANN("Probefeuerwehrmann", "PFM"),
    FEUERWEHRMANN("Feuerwehrmann", "FM"),
    OBERFEUERWEHRMANN("Oberfeuerwehrmann", "OFM"),
    HAUPTFEUERWEHRMANN("Hauptfeuerwehrmann", "HFM"),

    LOESCHMEISTER("Löschmeister", "LM"),
    OBERLOESCHMEISTER("Oberlöschmeister", "OLM"),
    HAUPTLOESCHMEISTER("Hauptlöschmeister", "HLM"),
    BRANDMEISTER("Brandmeister", "BM"),
    OBERBRANDMEISTER("Oberbrandmeister", "OBM"),
    HAUPTBRANDMEISTER("Hauptbrandmeister", "HBM"),
    BRANDINSPEKTOR("Brandinspektor", "BI"),
    OBERBRANDINSPEKTOR("Oberbrandinspektor", "OBI"),
    HAUPTBRANDINSPEKTOR("Hauptbrandinspektor", "HBI"),
    ABSCHNITTSBRANDINSPEKTOR("Abschnittsbrandinspektor", "ABI"),
    BRANDRAT("Brandrat", "BR"),
    OBERBRANDRAT("Oberbrandrat", "OBR"),
    LANDESFEUERWEHRRAT("Landesfeuerwehrrat", "LFR"),
    LANDESBRANDDIREKTOR_STV("Landesbranddirektor-Stv.", "LBDS"),
    LANDESBRANDDIREKTOR("Landesbranddirektor", "LBD"),

    LOESCHMEISTER_D_VERWALTUNG("Löschmeister der Verwaltung", "LM d.V."),
    OBERLOESCHMEISTER_D_VERWALTUNG("Oberlöschmeister der Verwaltung", "OLM d.V."),
    HAUPTLOESCHMEISTER_D_VERWALTUNG("Hauptlöschmeister der Verwaltung", "HLM d.V."),
    BRANDMEISTER_D_VERWALTUNG("Brandmeister der Verwaltung", "BM d.V."),
    OBERBRANDMEISTER_D_VERWALTUNG("Oberbrandmeister der Verwaltung", "OBM d.V."),
    HAUPTBRANDMEISTER_D_VERWALTUNG("Hauptbrandmeister der Verwaltung", "HBM d.V."),
    BRANDINSPEKTOR_D_VERWALTUNG("Brandinspektor der Verwaltung", "BI d.V."),
    OBERBRANDINSPEKTOR_D_VERWALTUNG("Oberbrandinspektor der Verwaltung", "OBI d.V."),
    HAUPTBRANDINSPEKTOR_D_VERWALTUNG("Hauptbrandinspektor der Verwaltung", "HBI d.V."),
    ABSCHNITTSBRANDINSPEKTOR_D_VERWALTUNG("Abschnittsbrandinspektor der Verwaltung", "ABI d.V."),
    BRANDRAT_D_VERWALTUNG("Brandrat der Verwaltung", "BR d.V."),

    LOESCHMEISTER_D_FACHDIENSTES("Löschmeister des Fachdienstes", "LM d.F."),
    OBERLOESCHMEISTER_D_FACHDIENSTES("Oberlöschmeister des Fachdienstes", "OLM d.F."),
    HAUPTLOESCHMEISTER_D_FACHDIENSTES("Hauptlöschmeister des Fachdienstes", "HLM d.F."),
    BRANDMEISTER_D_FACHDIENSTES("Brandmeister des Fachdienstes", "BM d.F."),
    OBERBRANDMEISTER_D_FACHDIENSTES("Oberbrandmeister des Fachdienstes", "OBM d.F."),
    HAUPTBRANDMEISTER_D_FACHDIENSTES("Hauptbrandmeister des Fachdienstes", "HBM d.F."),
    BRANDINSPEKTOR_D_FACHDIENSTES("Brandinspektor des Fachdienstes", "BI d.F."),
    OBERBRANDINSPEKTOR_D_FACHDIENSTES("Oberbrandinspektor des Fachdienstes", "OBI d.F."),
    HAUPTBRANDINSPEKTOR_D_FACHDIENSTES("Hauptbrandinspektor des Fachdienstes", "HBI d.F."),
    ABSCHNITTSBRANDINSPEKTOR_D_FACHDIENSTES("Abschnittsbrandinspektor des Fachdienstes", "ABI d.F."),
    BRANDRAT_D_FACHDIENSTES("Brandrat des Fachdienstes", "BR d.F."),
    OBERBRANDRAT_D_FACHDIENSTES("Oberbrandrat des Fachdienstes", "OBR d.F."),

    LOESCHMEISTER_D_SANITATSDIENSTES("Löschmeister des Sanitätsdienstes", "LM d.S."),
    OBERLOESCHMEISTER_D_SANITATSDIENSTES("Oberlöschmeister des Sanitätsdienstes", "OLM d.S."),
    HAUPTLOESCHMEISTER_D_SANITATSDIENSTES("Hauptlöschmeister des Sanitätsdienstes", "HLM d.S."),
    BRANDMEISTER_D_SANITATSDIENSTES("Brandmeister des Sanitätsdienstes", "BM d.S."),
    OBERBRANDMEISTER_D_SANITATSDIENSTES("Oberbrandmeister des Sanitätsdienstes", "OBM d.S."),
    HAUPTBRANDMEISTER_D_SANITATSDIENSTES("Hauptbrandmeister des Sanitätsdienstes", "HBM d.S."),
    BRANDINSPEKTOR_D_SANITATSDIENSTES("Brandinspektor des Sanitätsdienstes", "BI d.S."),
    OBERBRANDINSPEKTOR_D_SANITATSDIENSTES("Oberbrandinspektor des Sanitätsdienstes", "OBI d.S."),
    HAUPTBRANDINSPEKTOR_D_SANITATSDIENSTES("Hauptbrandinspektor des Sanitätsdienstes", "HBI d.S."),
    ABSCHNITTSBRANDINSPEKTOR_D_SANITATSDIENSTES("Abschnittsbrandinspektor des Sanitätsdienstes", "ABI d.S."),
    BRANDRAT_D_SANITATSDIENSTES("Brandrat des Sanitätsdienstes", "BR d.S."),
    FEUERWEHRARZT("Feuerwehrarzt", "FA"),
    FEUERWEHRVETERINAER("Feuerwehrveterinär", "FVET"),
    BEREICHSFEUERWEHRARZT("Bereichsfeuerwehrarzt", "BFA"),
    BEREICHSFEUERWEHRVETERINAER("Bereichsfeuerwehrveterinär", "BFVET"),
    LANDESFEUERWEHRARZT("Landesfeuerwehrarzt", "LFA"),
    LANDESFEUERWEHRVETERINAER("Landesfeuerwehrveterinär", "LFVET"),

    FEUERWEHRKURAT("Feuerwehrkurat", "FKUR"),
    BEREICHSFEUERWEHRKURAT("Bereichsfeuerwehrkurat", "BFKUR"),
    LANDESFEUERWEHRKURAT("Landesfeuerwehrkurat", "LFKUR");

    private final String fullBezeichnung;
    private final String shortBezeichnung;

    Dienstgrad(String fullBezeichnung, String shortBezeichnung) {
        this.fullBezeichnung = fullBezeichnung;
        this.shortBezeichnung = shortBezeichnung;
    }

    public String getFullBezeichnung() {
        return fullBezeichnung;
    }

    public String getShortBezeichnung() {
        return shortBezeichnung;
    }
}
