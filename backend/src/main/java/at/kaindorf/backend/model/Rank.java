package at.kaindorf.backend.model;

public enum Rank {
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

    LOESCHMEISTER_VERWALTUNG("Löschmeister der Verwaltung", "LM d.V."),
    OBERLOESCHMEISTER_VERWALTUNG("Oberlöschmeister der Verwaltung", "OLM d.V."),
    HAUPTLOESCHMEISTER_VERWALTUNG("Hauptlöschmeister der Verwaltung", "HLM d.V."),
    BRANDMEISTER_VERWALTUNG("Brandmeister der Verwaltung", "BM d.V."),
    OBERBRANDMEISTER_VERWALTUNG("Oberbrandmeister der Verwaltung", "OBM d.V."),
    HAUPTBRANDMEISTER_VERWALTUNG("Hauptbrandmeister der Verwaltung", "HBM d.V."),
    BRANDINSPEKTOR_VERWALTUNG("Brandinspektor der Verwaltung", "BI d.V."),
    OBERBRANDINSPEKTOR_VERWALTUNG("Oberbrandinspektor der Verwaltung", "OBI d.V."),
    HAUPTBRANDINSPEKTOR_VERWALTUNG("Hauptbrandinspektor der Verwaltung", "HBI d.V."),
    ABSCHNITTSBRANDINSPEKTOR_VERWALTUNG("Abschnittsbrandinspektor der Verwaltung", "ABI d.V."),
    BRANDRAT_VERWALTUNG("Brandrat der Verwaltung", "BR d.V."),

    LOESCHMEISTER_FACHDIENSTES("Löschmeister des Fachdienstes", "LM d.F."),
    OBERLOESCHMEISTER_FACHDIENSTES("Oberlöschmeister des Fachdienstes", "OLM d.F."),
    HAUPTLOESCHMEISTER_FACHDIENSTES("Hauptlöschmeister des Fachdienstes", "HLM d.F."),
    BRANDMEISTER_FACHDIENSTES("Brandmeister des Fachdienstes", "BM d.F."),
    OBERBRANDMEISTER_FACHDIENSTES("Oberbrandmeister des Fachdienstes", "OBM d.F."),
    HAUPTBRANDMEISTER_FACHDIENSTES("Hauptbrandmeister des Fachdienstes", "HBM d.F."),
    BRANDINSPEKTOR_FACHDIENSTES("Brandinspektor des Fachdienstes", "BI d.F."),
    OBERBRANDINSPEKTOR_FACHDIENSTES("Oberbrandinspektor des Fachdienstes", "OBI d.F."),
    HAUPTBRANDINSPEKTOR_FACHDIENSTES("Hauptbrandinspektor des Fachdienstes", "HBI d.F."),
    ABSCHNITTSBRANDINSPEKTOR_FACHDIENSTES("Abschnittsbrandinspektor des Fachdienstes", "ABI d.F."),
    BRANDRAT_FACHDIENSTES("Brandrat des Fachdienstes", "BR d.F."),
    OBERBRANDRAT_FACHDIENSTES("Oberbrandrat des Fachdienstes", "OBR d.F."),

    LOESCHMEISTER_SANITATSDIENSTES("Löschmeister des Sanitätsdienstes", "LM d.S."),
    OBERLOESCHMEISTER_SANITATSDIENSTES("Oberlöschmeister des Sanitätsdienstes", "OLM d.S."),
    HAUPTLOESCHMEISTER_SANITATSDIENSTES("Hauptlöschmeister des Sanitätsdienstes", "HLM d.S."),
    BRANDMEISTER_SANITATSDIENSTES("Brandmeister des Sanitätsdienstes", "BM d.S."),
    OBERBRANDMEISTER_SANITATSDIENSTES("Oberbrandmeister des Sanitätsdienstes", "OBM d.S."),
    HAUPTBRANDMEISTER_SANITATSDIENSTES("Hauptbrandmeister des Sanitätsdienstes", "HBM d.S."),
    BRANDINSPEKTOR_SANITATSDIENSTES("Brandinspektor des Sanitätsdienstes", "BI d.S."),
    OBERBRANDINSPEKTOR_SANITATSDIENSTES("Oberbrandinspektor des Sanitätsdienstes", "OBI d.S."),
    HAUPTBRANDINSPEKTOR_SANITATSDIENSTES("Hauptbrandinspektor des Sanitätsdienstes", "HBI d.S."),
    ABSCHNITTSBRANDINSPEKTOR_SANITATSDIENSTES("Abschnittsbrandinspektor des Sanitätsdienstes", "ABI d.S."),
    BRANDRAT_SANITATSDIENSTES("Brandrat des Sanitätsdienstes", "BR d.S."),
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

    Rank(String fullBezeichnung, String shortBezeichnung) {
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
