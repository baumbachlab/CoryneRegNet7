<%--
    Document   : tutorials
    Created on : Dec 1, 2019, 10:29:30 AM
    Author     : doglas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CoryneRegNet 7.0</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/tutorial.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
    </head>

    <body style="background-image: url('images/background.png'); background-size: cover;" >

    <nav class="navbar navbar-expand-md navbar-inverse bg-dark fixed-top navbar-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.htm"><span class="color logo-size">Coryne</span><span class="color-reg logo-size">Reg</span><span class="color logo-size">Net</span><span class="color-reg logo-size"> 7</span></a>
            </div>
            <div class="w-105 text-right">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse flex-grow-1 text-right" id="collapsibleNavbar">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link menu-item link-color flex-nowrap" href="databaseStatistics.htm">Statistics</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link menu-item link-color flex-nowrap" href="processToDownalod.htm">Download</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link menu-item link-color" href="#">Docs & Help</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link menu-item link-color flex-nowrap" href="workflow.htm">Workflow</a>
                    </li>
                    <li class="nav-item">
                        <a data-trigger="focus" class="nav-link menu-item link-color" href="requirements.htm">Requirements</a>
                    </li>
                </ul>
            </div>  
        </div>
    </nav>

    <div id="browser-messager" class="container-fluid" style="display: none;">
        <div class="alert alert-warning alert-dismissible centered-top" style="margin-bottom: -150px;">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            CoryneRegNet is better visualized in Google Chrome, Mozilla Firefox, Opera and Internet Explorer 10+.
        </div>
    </div>

    <div class="container badge-light shadow space-to-footer justify-content-center align-items-cente" style="margin-bottom: 150px">
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8 font">
                <div class="tutorial-title-size centered-top">
                    Welcome to CoryneRegNet 7 documentation and help
                </div>
            </div>
            <div class="col-sm-2"></div>
        </div>
        <hr style="color: #eee; margin-bottom: 10px">
        <div id="accordion">
            <div class="card">
                <div class="card-header">
                    <a class="card-link" data-toggle="collapse" href="#collapseOne" style="color: blue">
                        Transcriptional Regulatory Networks (TRNs)
                    </a>
                </div>
                <div id="collapseOne" class="collapse" data-parent="#accordion">
                    <div class="card-body">
                        <p>Regulatory proteins, also called Transcription Factors (TF) interact with the DNA through specific binding sites called Transcription Factor Binding Sites (TFBS) in order to activate or repress the expression of specific genes or operons. Transcriptional Regulatory Networks (TRN) are usually represented as directed graphs where the TFs and their Target Genes (TGs) are the nodes and the criterion to create an edge is the regulatory interaction between the TF and TG.</p>
                        <p>Further information about Transcription regulation and Transcriptional/Gene Regulatory Networks can be found in the following materials:</p>
                        <ul>
                            <li><p>Lee, T.I., Rinaldi, N.J., Robert, F., Odom, D.T., Bar-Joseph, Z., Gerber, G.K., Hannett, N.M., Harbison, C.T., Thompson, C.M., Simon, I. and Zeitlinger, J., 2002. <i>Saccharomyces cerevisiae</i> <a href="https://science.sciencemag.org/content/298/5594/799" style="color: blue" target="_blank">Transcriptional regulatory networks in Saccharomyces cerevisiae.</a> science, 298(5594), pp.799-804.</p></li>
                            <li><p>Teichmann, S.A. and Babu, M.M., 2004. <a class="link-tutorial" href="https://www.nature.com/articles/ng1340" style="color: blue" target="_blank">Gene regulatory network growth by duplication.</a> Nature genetics, 36(5), p.492.</p></li>
                            <li><p>Baumbach, J., Brinkrolf, K., Czaja, L.F., Rahmann, S. and Tauch, A., 2006. <a class="link-tutorial" href="https://bmcgenomics.biomedcentral.com/articles/10.1186/1471-2164-7-24" style="color: blue" target="_blank">CoryneRegNet: an ontology-based data warehouse of corynebacterial transcription factors and regulatory networks.</a> BMC genomics, 7(1), p.24.</p></li>
                            <li><p>Baumbach, J., Rahmann, S. and Tauch, A., 2009. <a href="https://bmcsystbiol.biomedcentral.com/articles/10.1186/1752-0509-3-8" style="color: blue" target="_blank">Reliable transfer of transcriptional gene regulatory networks between taxonomically related organisms.</a> BMC systems biology, 3(1), p.8.</p></li>
                            <li><p>Babu, M.M., Lang, B. and Aravind, L., 2009. <a href="https://link.springer.com/protocol/10.1007/978-1-59745-243-4_8" style="color: blue" target="_blank">Methods to reconstruct and compare transcriptional regulatory networks.</a> In Computational Systems Biology (pp. 163-180). Humana Press.</p></li>
                            <li><p>Thompson, D., Regev, A. and Roy, S., 2015. <a href="https://www.annualreviews.org/doi/abs/10.1146/annurev-cellbio-100913-012908?casa_token=1X9IDMhwOZcAAAAA%3ANvsSzd1Ef4znFbLffPNT5TfJsi2eqAEf2V7v32vngG30wggZg-NfU0hkxY8r2yxPkEqv7VZPR0gO" style="color: blue" target="_blank">Comparative analysis of gene regulatory networks: from network reconstruction to evolution.</a> Annual review of cell and developmental biology, 31, pp.399-428.</p></li>
                            <li><p>Kılıç, S. and Erill, I., 2016. <a href="https://bmcbioinformatics.biomedcentral.com/articles/10.1186/s12859-016-1113-7" style="color: blue" target="_blank">Assessment of transfer methods for comparative genomics of regulatory networks in bacteria.</a> BMC bioinformatics, 17(8), p.277.</p></li>
                            <li><p>Krebs, J.E., Goldstein, E.S. and Kilpatrick, S.T., 2017. <a href="https://www.jblearning.com/catalog/productdetails/9781284104493" style="color: blue" target="_blank">Lewin's genes XII.</a> Jones & Bartlett Learning.</p></li>
                            <li><p>Hao, T., Wu, D., Zhao, L., Wang, Q., Wang, E. and Sun, J., 2018. <a href="https://www.frontiersin.org/articles/10.3389/fmicb.2018.00296/full" style="color: blue" target="_blank">The genome-scale integrated networks in microorganisms.</a> Frontiers in microbiology, 9, p.296.</p></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <a class="collapsed card-link" data-toggle="collapse" href="#collapseTwo" style="color: blue">
                        CoryneRegNet's experimental TRNs literature
                    </a>
                </div>
                <div id="collapseTwo" class="collapse" data-parent="#accordion">
                    <div class="card-body">
                        <p><b>CoryneRegNet experimental collection:</b></p>
                        <p><i>Corynebacterium glutamicum</i> ATCC 13032:</p>
                        <ul>
                            <li><p>Pauling, J., Röttger, R., Tauch, A., Azevedo, V. and Baumbach, J., 2011. <a href="https://academic.oup.com/nar/article/40/D1/D610/2903480" style="color: blue" target="_blank">CoryneRegNet 6.0—Updated database content, new analysis methods and novel features focusing on community demands.</a>  Nucleic acids research, 40(D1), pp.D610-D614.</p></li>
                            <li><p>Freyre-González, J.A. and Tauch, A., 2017. <a href="https://www.sciencedirect.com/science/article/pii/S0168165616315887" style="color: blue" target="_blank">Functional architecture and global properties of the Corynebacterium glutamicum regulatory network: Novel insights from a dataset with a high genomic coverage.</a> Journal of biotechnology, 257, pp.199-210.</p></li>
                        </ul>
                        <p><i>Ecoli coli</i> K-12:</p>
                        <ul>
                            <li><p>Santos-Zavaleta, A., Salgado, H., Gama-Castro, S., Sánchez-Pérez, M., Gómez-Romero, L., Ledezma-Tejeida, D., García-Sotelo, J.S., Alquicira-Hernández, K., Muñiz-Rascado, L.J., Peña-Loredo, P. and Ishida-Gutiérrez, C., 2018. <a href="https://academic.oup.com/nar/article/47/D1/D212/5160972" style="color: blue" target="_blank">RegulonDB v 10.5: tackling challenges to unify classic and high throughput knowledge of gene regulation in E. coli K-12.</a> Nucleic acids research, 47(D1), pp.D212-D220.</p></li>
                        </ul>
                        <p><i>Mycobacterium tuberculosis</i> H37Rv:</p>
                        <ul>
                            <li><p>Minch, K.J., Rustad, T.R., Peterson, E.J., Winkler, J., Reiss, D.J., Ma, S., Hickey, M., Brabant, W., Morrison, B., Turkarslan, S. and Mawhinney, C., 2015. <a href="https://www.nature.com/articles/ncomms6829" style="color: blue" target="_blank">The DNA-binding network of Mycobacterium tuberculosis.</a> Nature communications, 6, p.5829.</p></li>
                        </ul>
                        <p><i>Bacillus subtilis</i> 168:</p>
                        <ul>
                            <li><p>Sierro, N., Makita, Y., de Hoon, M. and Nakai, K., 2007. <a href="https://academic.oup.com/nar/article/36/suppl_1/D93/2507686" style="color: blue" target="_blank">DBTBS: a database of transcriptional regulation in Bacillus subtilis containing upstream intergenic conservation information.</a> Nucleic acids research, 36(suppl_1), pp.D93-D96.</p></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <a class="collapsed card-link" data-toggle="collapse" href="#collapseThree" style="color: blue">
                        Corynebacterial transcriptional regulation literature
                    </a>
                </div>
                <div id="collapseThree" class="collapse" data-parent="#accordion">
                    <div class="card-body">
                        <p>Information about Corynebacterial Transcriptional/Gene Regulatory Networks found in the literature:</p>
                        <ul>
                            <li><p>Jakoby, M., Nolden, L., Meier‐Wagner, J., Krämer, R. and Burkovski, A., 2000. <a href="https://onlinelibrary.wiley.com/doi/full/10.1046/j.1365-2958.2000.02073.x" style="color: blue" target="_blank">AmtR, a global repressor in the nitrogen regulation system of Corynebacterium glutamicum.</a> Molecular microbiology, 37(4), pp.964-977.</p></li>
                            <li><p>Rey, D.A., Pühler, A. and Kalinowski, J., 2003.  <a href="https://www.sciencedirect.com/science/article/pii/S0168165603000737" style="color: blue" target="_blank">The putative transcriptional repressor McbR, member of the TetR-family, is involved in the regulation of the metabolic network directing the synthesis of sulfur containing amino acids in Corynebacterium glutamicum.</a> Journal of biotechnology, 103(1), pp.51-65.</p></li>
                            <li><p>Engels, S., Schweitzer, J.E., Ludwig, C., Bott, M. and Schaffer, S., 2004. <a href="https://onlinelibrary.wiley.com/doi/full/10.1111/j.1365-2958.2003.03979.x" style="color: blue" target="_blank">clpC and clpP1P2 gene expression in Corynebacterium glutamicum is controlled by a regulatory network involving the transcriptional regulators ClgR and HspR as well as the ECF sigma factor σH.</a> Molecular microbiology, 52(1), pp.285-302.</p></li>
                            <li><p>Strösser, J., Lüdke, A., Schaffer, S., Krämer, R. and Burkovski, A., 2004. <a href="https://onlinelibrary.wiley.com/doi/full/10.1111/j.1365-2958.2004.04247.x" style="color: blue" target="_blank">Regulation of GlnK activity: modification, membrane sequestration and proteolysis as regulatory principles in the network of nitrogen control in Corynebacterium glutamicum.</a> Molecular microbiology, 54(1), pp.132-147.</p></li>
                            <li><p>Brune, I., Brinkrolf, K., Kalinowski, J., Pühler, A. and Tauch, A., 2005. <a href="https://bmcgenomics.biomedcentral.com/articles/10.1186/1471-2164-6-86" style="color: blue" target="_blank">The individual and common repertoire of DNA-binding transcriptional regulators of Corynebacterium glutamicum, Corynebacterium efficiens, Corynebacterium diphtheriae and Corynebacterium jeikeium deduced from the complete genome sequences.</a> BMC genomics, 6(1), p.86.</p></li>
                            <li><p>Rey, D.A., Nentwich, S.S., Koch, D.J., Rückert, C., Pühler, A., Tauch, A. and Kalinowski, J., 2005. <a href="https://onlinelibrary.wiley.com/doi/full/10.1111/j.1365-2958.2005.04586.x" style="color: blue" target="_blank">The McbR repressor modulated by the effector substance S‐adenosylhomocysteine controls directly the transcription of a regulon involved in sulphur metabolism of Corynebacterium glutamicum ATCC 13032.</a> Molecular microbiology, 56(4), pp.871-887.</p></li>
                            <li><p>Brune, I., Werner, H., Hüser, A.T., Kalinowski, J., Pühler, A. and Tauch, A., 2006. <a href="https://bmcgenomics.biomedcentral.com/articles/10.1186/1471-2164-7-21" style="color: blue" target="_blank">The DtxR protein acting as dual transcriptional regulator directs a global regulatory network involved in iron metabolism of Corynebacterium glutamicum.</a> BMC genomics, 7(1), p.21.</p></li>
                            <li><p>Wendisch, V.F., 2006. <a href="https://pdfs.semanticscholar.org/193d/e2e29ede2a44a9ad49e3067552cdd6c9bdb7.pdf" style="color: blue" target="_blank">Genetic regulation of Corynebacterium glutamicum metabolism.</a> Journal of microbiology and biotechnology, 16(7), p.999.</p></li>
                            <li><p>Brinkrolf, K., Brune, I. and Tauch, A., 2006.   <a href="https://geneticsmr.com/sites/default/files/articles/year2006/vol5-4/pdf/gmr0264.pdf" style="color: blue" target="_blank">Transcriptional regulation of catabolic pathways for aromatic compounds in Corynebacterium glutamicum.</a> Genetics and Molecular Research, 5(4), pp.773-789.</p></li>
                            <li><p>Nakunst, D., Larisch, C., Hüser, A.T., Tauch, A., Pühler, A. and Kalinowski, J., 2007. <a href="https://jb.asm.org/content/189/13/4696.short" style="color: blue" target="_blank">The extracytoplasmic function-type sigma factor SigM of Corynebacterium glutamicum ATCC 13032 is involved in transcription of disulfide stress-related genes.</a>  Journal of bacteriology, 189(13), pp.4696-4707.</p></li>
                            <li><p>Cramer, A. and Eikmanns, B.J., 2007. <a href="https://www.karger.com/Article/Abstract/96459" style="color: blue" target="_blank">RamA, the transcriptional regulator of acetate metabolism in Corynebacterium glutamicum, is subject to negative autoregulation.</a> Journal of molecular microbiology and biotechnology, 12(1-2), pp.51-59.</p></li>
                            <li><p>Cramer, A., Auchter, M., Frunzke, J., Bott, M. and Eikmanns, B.J., 2007. <a href="https://jb.asm.org/content/189/3/1145.short" style="color: blue" target="_blank">RamB, the transcriptional regulator of acetate metabolism in Corynebacterium glutamicum, is subject to regulation by RamA and RamB.</a> Journal of bacteriology, 189(3), pp.1145-1149.</p></li>
                            <li><p>Brinkrolf, K., Brune, I. and Tauch, A., 2007. <a href="https://www.sciencedirect.com/science/article/pii/S0168165606010340" style="color: blue" target="_blank">The transcriptional regulatory network of the amino acid producer Corynebacterium glutamicum.</a> Journal of biotechnology, 129(2), pp.191-211.</p></li>
                            <li><p>Bott, M., 2007. <a href="https://www.sciencedirect.com/science/article/abs/pii/S0966842X07001539" style="color: blue" target="_blank">Offering surprises: TCA cycle regulation in Corynebacterium glutamicum.</a> Trends in microbiology, 15(9), pp.417-425.</p></li>
                            <li><p>Jungwirth, B., Emer, D., Brune, I., Hansmeier, N., Pühler, A., Eikmanns, B.J. and Tauch, A., 2008. <a href="https://academic.oup.com/femsle/article/281/2/190/474955" style="color: blue" target="_blank">Triple transcriptional control of the resuscitation promoting factor 2 (rpf2) gene of Corynebacterium glutamicum by the regulators of acetate metabolism RamA and RamB and the cAMP-dependent regulator GlxR.</a> FEMS microbiology letters, 281(2), pp.190-197.</p></li>
                            <li><p>Kohl, T.A., Baumbach, J., Jungwirth, B., Pühler, A. and Tauch, A., 2008. <a href="https://www.sciencedirect.com/science/article/pii/S0168165608002332" style="color: blue" target="_blank">The GlxR regulon of the amino acid producer Corynebacterium glutamicum: in silico and in vitro detection of DNA binding sites of a global transcription regulator.</a> Journal of biotechnology, 135(4), pp.340-350.</p></li>
                            <li><p>Ehira, S., Teramoto, H., Inui, M. and Yukawa, H., 2009. <a href="https://jb.asm.org/content/191/9/2964.short" style="color: blue" target="_blank">Regulation of Corynebacterium glutamicum heat shock response by the extracytoplasmic-function sigma factor SigH and transcriptional regulators HspR and HrcA.</a> Journal of bacteriology, 191(9), pp.2964-2972.</p></li>
                            <li><p>Jochmann, N., Kurze, A.K., Czaja, L.F., Brinkrolf, K., Brune, I., Hüser, A.T., Hansmeier, N., Pühler, A., Borovok, I. and Tauch, A., 2009.  <a href="https://www.microbiologyresearch.org/content/journal/micro/10.1099/mic.0.025841-0" style="color: blue" target="_blank">Genetic makeup of the Corynebacterium glutamicum LexA regulon deduced from comparative transcriptomics and in vitro DNA band shift assays.</a> Microbiology, 155(5), pp.1459-1477.</p></li>
                            <li><p>Kohl, T.A. and Tauch, A., 2009. <a href="https://www.sciencedirect.com/science/article/pii/S0168165609003496" style="color: blue" target="_blank">The GlxR regulon of the amino acid producer Corynebacteriumglutamicum: Detection of the corynebacterial core regulon and integration into the transcriptional regulatory network model.</a> Journal of biotechnology, 143(4), pp.239-246.</p></li>
                            <li><p>Venancio, T.M. and Aravind, L., 2009. <a href="https://jbiol.biomedcentral.com/articles/10.1186/jbiol132" style="color: blue" target="_blank">Reconstructing prokaryotic transcriptional regulatory networks: lessons from actinobacteria.</a> Journal of biology, 8(3), p.29.</p></li>
                            <li><p>Schröder, J., Jochmann, N., Rodionov, D.A. and Tauch, A., 2010. <a href="https://bmcgenomics.biomedcentral.com/articles/10.1186/1471-2164-11-12" style="color: blue" target="_blank">The Zur regulon of Corynebacterium glutamicum ATCC 13032.</a> BMC genomics, 11(1), p.12.</p></li>
                            <li><p>Brinkrolf, K., Schröder, J., Pühler, A. and Tauch, A., 2010. <a href="https://www.sciencedirect.com/science/article/pii/S0168165609005549" style="color: blue" target="_blank">The transcriptional regulatory repertoire of Corynebacterium glutamicum: reconstruction of the network controlling pathways involved in lysine and glutamate production.</a> Journal of biotechnology, 149(3), pp.173-182.</p></li>
                            <li><p>Schröder, J. and Tauch, A., 2010. <a href="https://academic.oup.com/femsre/article/34/5/685/796936" style="color: blue" target="_blank">Transcriptional regulation of gene expression in Corynebacterium glutamicum: the role of global, master and local regulators in the modular and hierarchical gene regulatory network..</a> FEMS microbiology reviews, 34(5), pp.685-737.</p></li>
                            <li><p>Pátek, M. and Nešvera, J., 2011. <a href="https://www.sciencedirect.com/science/article/pii/S0168165611000617" style="color: blue" target="_blank">Sigma factors and promoters in Corynebacterium glutamicum.</a> Journal of biotechnology, 154(2-3), pp.101-113.</p></li>
                            <li><p>Teramoto, H., Inui, M. and Yukawa, H., 2011. <a href="https://www.sciencedirect.com/science/article/pii/S0168165611000605" style="color: blue" target="_blank">Transcriptional regulators of multiple genes involved in carbon metabolism in Corynebacterium glutamicum.</a> Journal of biotechnology, 154(2-3), pp.114-125.</p></li>
                            <li><p>Brune, I., Götker, S., Schneider, J., Rodionov, D.A. and Tauch, A., 2012. <a href="https://www.sciencedirect.com/science/article/pii/S0168165611006572" style="color: blue" target="_blank">Negative transcriptional control of biotin metabolism genes by the TetR-type regulator BioQ in biotin-auxotrophic Corynebacterium glutamicum ATCC 13032.</a> Journal of biotechnology, 159(3), pp.225-234.</p></li>
                            <li><p>Barzantny, H., Schröder, J., Strotmeier, J., Fredrich, E., Brune, I. and Tauch, A., 2012. <a href="https://www.sciencedirect.com/science/article/pii/S0168165612000697" style="color: blue" target="_blank">The transcriptional regulatory network of Corynebacterium jeikeium K411 and its interaction with metabolic routes contributing to human body odor formation.</a> Journal of biotechnology, 159(3), pp.235-248.</p></li>
                            <li><p>Busche, T., Šilar, R., Pičmanová, M., Pátek, M. and Kalinowski, J., 2012. <a href="https://bmcgenomics.biomedcentral.com/articles/10.1186/1471-2164-13-445" style="color: blue" target="_blank">Transcriptional regulation of the operon encoding stress-responsive ECF sigma factor SigH and its anti-sigma factor RshA, and control of its regulatory network in Corynebacterium glutamicum.</a> BMC genomics, 13(1), p.445.</p></li>
                            <li><p>Toyoda, K., Teramoto, H., Gunji, W., Inui, M. and Yukawa, H., 2013. <a href="https://jb.asm.org/content/195/8/1718.short" style="color: blue" target="_blank">Involvement of regulatory interactions among global regulators GlxR, SugR, and RamA in expression of ramA in Corynebacterium glutamicum.</a> Journal of bacteriology, 195(8), pp.1718-1726.</p></li>
                            <li><p>Jungwirth, B., Sala, C., Kohl, T.A., Uplekar, S., Baumbach, J., Cole, S.T., Pühler, A. and Tauch, A., 2013. <a href="https://www.microbiologyresearch.org/content/journal/micro/10.1099/mic.0.062059-0" style="color: blue" target="_blank">High-resolution detection of DNA binding sites of the global transcriptional regulator GlxR in Corynebacterium glutamicum.</a> Microbiology, 159(1), pp.12-22.</p></li>
                            <li><p>Toyoda, K., Teramoto, H., Yukawa, H. and Inui, M., 2015. <a href="https://jb.asm.org/content/197/3/483.short" style="color: blue" target="_blank">Expanding the regulatory network governed by the extracytoplasmic function sigma factor σH in Corynebacterium glutamicum.</a> Journal of bacteriology, 197(3), pp.483-496.</p></li>
                            <li><p>Burkovski, A. ed., 2015. <a href="https://www.caister.com/cory2" style="color: blue" target="_blank">Corynebacterium glutamicum.</a> Caister Academic Press.</p></li>
                            <li><p>Kuhlmann, N., Petrov, D.P., Henrich, A.W., Lindner, S.N., Wendisch, V.F. and Seibold, G.M., 2015. <a href="https://www.microbiologyresearch.org/content/journal/micro/10.1099/mic.0.000134" style="color: blue" target="_blank">Transcription of malP is subject to phosphotransferase system-dependent regulation in Corynebacterium glutamicum.</a> Microbiology, 161(9), pp.1830-1843.</p></li>
                            <li><p>Toyoda, K. and Inui, M., 2016. <a href="https://link.springer.com/article/10.1007/s00253-015-7074-3" style="color: blue" target="_blank">Regulons of global transcription factors in Corynebacterium glutamicum.</a> Applied microbiology and biotechnology, 100(1), pp.45-60.</p></li>
                            <li><p>Hong, E.J., Kim, P., Kim, E.S., Kim, Y. and Lee, H.S., 2016. <a href="https://www.sciencedirect.com/science/article/pii/S0923250815001606" style="color: blue" target="_blank">Involvement of the osrR gene in the hydrogen peroxide-mediated stress response of Corynebacterium glutamicum.</a> Research in microbiology, 167(1), pp.20-28.</p></li>
                            <li><p>Castro, T.L., Seyffert, N., Pinto, A.C., Silva, A., Azevedo, V. and Pacheco, L.G., 2016. <a href="https://onlinelibrary.wiley.com/doi/10.1002/9781119004813.ch28" style="color: blue" target="_blank">Extracytoplasmic Function Sigma Factors and Stress Responses in Corynebacterium pseudotuberculosis.</a> Stress and Environmental Regulation of Gene Expression and Adaptation in Bacteria, pp.321-327.</p></li>
                            <li><p>Busche, T. and Kalinowski, J., 2016. <a href="https://onlinelibrary.wiley.com/doi/10.1002/9781119004813.ch31" style="color: blue" target="_blank">The ECF family sigma factor 𝛔H in Corynebacterium glutamicum controls the thiol-oxidative stress.</a> Stress and Environmental Regulation of Gene Expression and Adaptation in Bacteria, p.352.</p></li>
                            <li><p>Toyoda, K. and Inui, M., 2016. <a href="https://link.springer.com/article/10.1007/s00253-015-7074-3" style="color: blue" target="_blank">Regulons of global transcription factors in Corynebacterium glutamicum.</a> Applied microbiology and biotechnology, 100(1), pp.45-60.</p></li>
                            <li><p>Taniguchi, H., 2016.  <a href="https://pub.uni-bielefeld.de/record/2903682" style="color: blue" target="_blank">Exploring the potential of sigma factors for strain development in Corynebacterium glutamicum.</a></p></li>
                            <li><p>Šilar, R., Holátko, J., Rucká, L., Rapoport, A., Dostálová, H., Kadeřábková, P., Nešvera, J. and Pátek, M., 2016. <a href="https://link.springer.com/article/10.1007/s00284-016-1077-x" style="color: blue" target="_blank">Use of in vitro transcription system for analysis of Corynebacterium glutamicum promoters recognized by two sigma factors.</a> Current microbiology, 73(3), pp.401-408.</p></li>
                            <li><p>Liu, X., Yang, S., Wang, F., Dai, X., Yang, Y. and Bai, Z., 2017. <a href="https://link.springer.com/article/10.1007/s10295-016-1854-3" style="color: blue" target="_blank">Comparative analysis of the Corynebacterium glutamicum transcriptome in response to changes in dissolved oxygen levels.</a> Journal of industrial microbiology & biotechnology, 44(2), pp.181-195.</p></li>
                            <li><p>Freyre-González, J.A. and Tauch, A., 2017. <a href="https://www.sciencedirect.com/science/article/pii/S0168165616315887" style="color: blue" target="_blank">Functional architecture and global properties of the Corynebacterium glutamicum regulatory network: Novel insights from a dataset with a high genomic coverage.</a> Journal of biotechnology, 257, pp.199-210.</p></li>
                            <li><p>Dostálová, H., Holátko, J., Busche, T., Rucká, L., Rapoport, A., Halada, P., Nešvera, J., Kalinowski, J. and Pátek, M., 2017. <a href="https://amb-express.springeropen.com/articles/10.1186/s13568-017-0436-8" style="color: blue" target="_blank">Assignment of sigma factors of RNA polymerase to promoters in Corynebacterium glutamicum.</a> AMB Express, 7(1), p.133.</p></li>
                            <li><p>Shah, A., Blombach, B., Gauttam, R. and Eikmanns, B.J., 2018. <a href="https://link.springer.com/article/10.1007/s00253-018-9085-3" style="color: blue" target="_blank">The RamA regulon: complex regulatory interactions in relation to central metabolism in Corynebacterium glutamicum.</a> Applied microbiology and biotechnology, 102(14), pp.5901-5910.</p></li>
                            <li><p>Wittchen, M., Busche, T., Gaspar, A.H., Lee, J.H., Ton-That, H., Kalinowski, J. and Tauch, A., 2018. <a href="https://bmcgenomics.biomedcentral.com/articles/10.1186/s12864-018-4481-8" style="color: blue" target="_blank">Transcriptome sequencing of the human pathogen Corynebacterium diphtheriae NCTC 13129 provides detailed insights into its transcriptional landscape and into DtxR-mediated transcriptional regulation.</a> BMC genomics, 19(1), p.82.</p></li>
                            <li><p>Keppel, M., Piepenbreier, H., Gätgens, C., Fritz, G. and Frunzke, J., 2019. <a href="https://onlinelibrary.wiley.com/doi/full/10.1111/mmi.14226?casa_token=Pcwv6Fr59L0AAAAA%3A2K9xG-YDm307sBsfwBrQevuClRAC0c9LcUO-QtYQRqyFBwEPfa_d0GuEWPEZ8rVnUMny8SRSYIJEdkA" style="color: blue" target="_blank">Toxic but tasty–temporal dynamics and network architecture of heme‐responsive two‐component signaling in Corynebacterium glutamicum.</a> Molecular microbiology, 111(5), pp.1367-1381.</p></li>
                            <li><p>Ibraim, I.C., Parise, M.T.D., Parise, D., Sfeir, M.Z.T., de Paula Castro, T.L., Wattam, A.R., Ghosh, P., Barh, D., Souza, E.M., Góes-Neto, A. and Gomide, A.C.P., Azevedo, V., 2019. <a href="https://link.springer.com/article/10.1186/s12864-019-6018-1" style="color: blue" target="_blank">Transcriptome profile of Corynebacterium pseudotuberculosis in response to iron limitation.</a> BMC genomics, 20(1), p.663.</p></li>
                            <li><p>Haas, T., Graf, M., Nieß, A., Busche, T., Kalinowski, J., Blombach, B. and Takors, R., 2019. <a href="https://www.frontiersin.org/articles/10.3389/fmicb.2019.00974/full" style="color: blue" target="_blank">Identifying the Growth Modulon of Corynebacterium glutamicum.</a> Frontiers in microbiology, 10, p.974.</p></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <a class="collapsed card-link" data-toggle="collapse" href="#collapseFour" style="color: blue">
                        CoryneRegNet's TRN transfer methodology
                    </a>
                </div>
                <div id="collapseFour" class="collapse" data-parent="#accordion">
                    <div class="card-body">
                        <p>CoryneRegNet transfer methodology is based on four steps: all-vs-all protein 
                            <a href="https://blast.ncbi.nlm.nih.gov/Blast.cgi?PAGE_TYPE=BlastDocs&DOC_TYPE=Download" style="color: blue" target="_blank">BLAST</a>, 
                            operon prediction, identification of upstream regions and predictions of conserved TFBSs using nhmmer from 
                            <a href="http://hmmer.org/" style="color: blue" target="_blank">HMMER</a> package. 
                            The following paragraphs summarize these steps:</p>
                        <ul>
                            <li><p>First, we downloaded all fully sequenced and annotated corynebacterial
                                    genomes available on 
                                    <a href="https://www.ncbi.nlm.nih.gov/" style="color: blue" target="_blank">NCBI</a> 
                                    web site (June 2019) and of our four model genomes 
                                    (<a href="https://www.ncbi.nlm.nih.gov/nuccore/BX927147.1" style="color: blue" target="_blank"><i>Corynebacterium glutamicum</i> ATCC 13032</a>, 
                                    <a href="https://www.ncbi.nlm.nih.gov/nuccore/NC_000913.3" style="color: blue" target="_blank"><i>Escherichia coli</i> K12</a>,
                                    <a href="https://www.ncbi.nlm.nih.gov/nuccore/NC_000964.3" style="color: blue" target="_blank"><i>Bacillus subtilis</i> 168</a> and 
                                    <a href="https://www.ncbi.nlm.nih.gov/nuccore/AL123456.3" style="color: blue" target="_blank"><i>Mycobacterium tuberculosis</i> H37Rv</a>). 
                                    Then we performed all-vs-all protein 
                                    <a href="https://blast.ncbi.nlm.nih.gov/Blast.cgi?PAGE_TYPE=BlastDocs&DOC_TYPE=Download" style="color: blue" target="_blank">BLAST
                                    </a> and selected the best blast hits (BBHs) with a cutoff of 10<sup>-10</sup> to predict the homologous proteins, among all 228 organisms.</p></li>
                            <li><p>In the second step we predicted operons for all target organisms by considering genes with an intergenic distance less than 50 base pairs as to part 
                                    of the same operon. 
                                    Operons of template organisms they were available in the respective TRN database.</p></li>
                            <li><p>Then, upstream regions (-560,+20) were identified.</p></li>
                            <li><p>To identify TFBSs we scanned the upstream regions using nhmmer from 
                                    <a href="http://hmmer.org/" style="color: blue" target="_blank">HMMER</a> package in order to predict conserved TFBSs. In this step we just scanned 
                                    the upstream region when the TF and TG (or first gene of operon) were pointed as homologous in the first step. The profile HMMs of the conserved TFs
                                    were applied to the upstream regions of the potentially regulated TGs by using HMMER’s default parameters, which corresponds to a p-value of ~10
                                    <sup>-5</sup>. When a TF, a TG and a TFBS are conserved we consider this as a conserved regulatory interaction. Regulatory interactions predicted to 
                                    the first gene of an operon were extended to the entire operon.
                                    The role of a predicted regulatory interaction is inherited from the model regulatory interaction. Finally, the predicted binding 
                                    sites are used to create HMM profiles for TFs of the target organisms with hmmbuild from <a href="http://hmmer.org/" style="color: blue" target="_blank">HMMER</a> 
                                    package. The interaction p-value was obtained by applying Tippet's method. The R package Metap was used to calculate the joint p-value of the p-values obtained in 
                                    the homology and motif searches.</p></li>

                        </ul>
                        <p>Software used in the methodology</p>
                        <table id="homologous-table" class="table table-striped" style="width:100%">
                            <thead>
                                <tr>
                                    <th>Software</th>
                                    <th>Description</th>
                                    <th>Publication</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><span><a href="https://blast.ncbi.nlm.nih.gov/Blast.cgi?PAGE_TYPE=BlastDocs&DOC_TYPE=Download" style="color: blue" target="_blank">BLAST</a></span></td>
                                    <td><span>"BLAST finds regions of similarity between biological sequences. The program compares nucleotide or protein sequences to sequence databases and calculates the statistical significance."</span></td>
                                    <td><span>Altschul, S.F., Madden, T.L., Schäffer, A.A., Zhang, J., Zhang, Z., Miller, W. and Lipman, D.J., 1997. <a href="https://academic.oup.com/nar/article/25/17/3389/1061651" style="color: blue" target="_blank">Gapped BLAST and PSI-BLAST: a new generation of protein database search programs.</a> Nucleic acids research, 25(17), pp.3389-3402.</span></td>
                                </tr>
                                <tr>
                                    <td><span><a href="http://www.clustal.org/omega/" style="color: blue" target="_blank">Clustal Omega</a></span></td>
                                    <td><span>"Clustal Omega can align virtually any number of protein sequences quickly and that delivers accurate alignments."</span></td>
                                    <td><span>Sievers, F. and Higgins, D.G., 2018. <a href="https://onlinelibrary.wiley.com/doi/full/10.1002/pro.3290" style="color: blue" target="_blank">Clustal Omega for making accurate alignments of many protein sequences.</a> Protein Science, 27(1), pp.135-145.</span></td>
                                </tr>
                                <tr>
                                    <td><span>nhmmer from <a href="http://hmmer.org/" style="color: blue" target="_blank">HMMER</a> package</span></td>
                                    <td><span>"nhmmer is a tool for DNA/DNA sequence comparison that is built on the HMMER framework, which applies probabilistic inference methods based on hidden Markov models to the problem of homology search."</span></td>
                                    <td><span>Wheeler, T.J. and Eddy, S.R., 2013. <a href="https://academic.oup.com/bioinformatics/article/29/19/2487/186765" style="color: blue" target="_blank">nhmmer: DNA homology search with profile HMMs.</a> Bioinformatics, 29(19), pp.2487-2489.</span></td>
                                </tr>
                                <tr>
                                    <td><span><a href="https://rdrr.io/cran/metap/" style="color: blue" target="_blank">Metap</a></span></td>
                                    <td><span>Metap is an R package that implements methods for the meta–analysis of p–values (significance values).</span></td>
                                    <td><span>Dewey, M., 2019. <a href="https://rdrr.io/cran/metap/f/inst/doc/metap.pdf" style="color: blue" target="_blank">Introduction to the metap package.</a></span></td>
                                </tr>
                                <tr>
                                    <td><span><a href="https://skylign.org/" style="color: blue" target="_blank">Skylign</a></span></td>
                                    <td><span>"Skylign is a tool for creating logos representing both sequence alignments and profile hidden Markov models".</span></td>
                                    <td><span>Wheeler, T.J., Clements, J. and Finn, R.D., 2014. <a href="https://bmcbioinformatics.biomedcentral.com/articles/10.1186/1471-2105-15-7" style="color: blue" target="_blank">Skylign: a tool for creating informative, interactive logos representing sequence alignments and profile hidden Markov models.</a> BMC bioinformatics, 15(1), p.7.</span></td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="row workflow-logo" style="padding-bottom: 50px">
                            <div class="col-sm-12">
                                <center>
                                    <img id="img-workflow" class="img-fluid img-responsive" src="images/workflow-final.png" alt="Workflow">
                                </center>
                            </div>
                        </div>
                        <p>Further information about CoryneRegNet Transcriptional Regulatory Networks transfer methodology:</p>
                        <ul>
                            <li><p>Baumbach, J., Rahmann, S. and Tauch, A., 2009. <a href="https://bmcsystbiol.biomedcentral.com/articles/10.1186/1752-0509-3-8" style="color: blue" target="_blank">Reliable transfer of transcriptional gene regulatory networks between taxonomically related organisms.</a> BMC systems biology, 3(1), p.8.</p></li>
                            <li><p>Baumbach, J., Wittkop, T., Kleindt, C.K. and Tauch, A., 2009. <a href="https://www.nature.com/articles/nprot.2009.81" style="color: blue" target="_blank">Integrated analysis and reconstruction of microbial transcriptional gene regulatory networks using CoryneRegNet.</a> Nature Protocols, 4(6), p.992.</p></li>
                            <li><p>Baumbach, J., 2010. <a href="https://academic.oup.com/nar/article/38/22/7877/1043007" style="color: blue" target="_blank">On the power and limits of evolutionary conservation—unraveling bacterial gene regulatory networks.</a> Nucleic acids research, 38(22), pp.7877-7884.</p></li>
                        </ul>

                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <a class="collapsed card-link" data-toggle="collapse" href="#collapseEight" style="color: blue">

                            CoryneRegNet's RNA methodology
                        </a>
                    </div>
                    <div id="collapseEight" class="collapse" data-parent="#accordion">
                        <div class="card-body">
                            Lorem ipsum..
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <a class="collapsed card-link" data-toggle="collapse" href="#collapseNine" style="color: blue">
                            CoryneRegNet's RNA transfer methodology
                        </a>
                    </div>
                    <div id="collapseNine" class="collapse" data-parent="#accordion">
                        <div class="card-body">
                            Lorem ipsum..
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <a class="collapsed card-link" data-toggle="collapse" href="#collapseFive" style="color: blue">
                            CoryneRegNet papers
                        </a>
                    </div>
                    <div id="collapseFive" class="collapse" data-parent="#accordion">
                        <div class="card-body">
                            <ul>
                                <li><p>Baumbach, J., Brinkrolf, K., Czaja, L.F., Rahmann, S. and Tauch, A., 2006. <a href="https://bmcgenomics.biomedcentral.com/articles/10.1186/1471-2164-7-24" style="color: blue" target="_blank">CoryneRegNet: an ontology-based data warehouse of corynebacterial transcription factors and regulatory networks.</a> BMC genomics, 7(1), p.24.</p></li>
                                <li><p>Baumbach, J., Brinkrolf, K., Wittkop, T., Tauch, A. and Rahmann, S., 2006. <a href="https://www.degruyter.com/view/j/jib.2006.3.issue-2/biecoll-jib-2006-24/biecoll-jib-2006-24.xml" style="color: blue" target="_blank">CoryneRegNet 2: an integrative bioinformatics approach for reconstruction and comparison of transcriptional regulatory networks in prokaryotes.</a> Journal of Integrative Bioinformatics, 3(2), pp.1-13.</p></li>
                                <li><p>Baumbach, J., Wittkop, T., Rademacher, K., Rahmann, S., Brinkrolf, K. and Tauch, A., 2007. <a href="https://www.sciencedirect.com/science/article/pii/S0168165606010339" style="color: blue" target="_blank">CoryneRegNet 3.0—an interactive systems biology platform for the analysis of gene regulatory networks in corynebacteria and Escherichia coli.</a>  Journal of biotechnology, 129(2), pp.279-289.</p></li>
                                <li><p>Baumbach, J., 2007. <a href="https://bmcbioinformatics.biomedcentral.com/articles/10.1186/1471-2105-8-429" style="color: blue" target="_blank">CoryneRegNet 4.0–A reference database for corynebacterial gene regulatory networks.</a> BMC bioinformatics, 8(1), p.429.</p></li>
                                <li><p>Baumbach, J., Rahmann, S. and Tauch, A., 2009. <a href="https://bmcsystbiol.biomedcentral.com/articles/10.1186/1752-0509-3-8" style="color: blue" target="_blank">Reliable transfer of transcriptional gene regulatory networks between taxonomically related organisms.</a> BMC systems biology, 3(1), p.8. [Methodological paper, not a database release]</p></li>
                                <li><p>Pauling, J., Röttger, R., Tauch, A., Azevedo, V. and Baumbach, J., 2011. <a href="https://academic.oup.com/nar/article/40/D1/D610/2903480" style="color: blue" target="_blank">CoryneRegNet 6.0—Updated database content, new analysis methods and novel features focusing on community demands.</a>  Nucleic acids research, 40(D1), pp.D610-D614.</p></li>
                            </ul>  
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <a class="collapsed card-link" data-toggle="collapse" href="#collapseSix" style="color: blue">
                            How to navigate in CoryneRegNet?
                        </a>
                    </div>
                    <div id="collapseSix" class="collapse" data-parent="#accordion">
                        <div class="card-body">
                            <p><b>Home Page</b></p>
                            <p>In the home page the user can choose which evidence level he/she is interested in.</p>
                            <ul>
                                <li><p>Experimental: all analysis and searches are performed on our four model organisms which have TRNs reconstructed experimentally.</p></li>
                                <li><p>Predicted: all analysis and searches are performed on all 228 organisms (templates and targets).</p></li>
                            </ul>
                            <p><b>Search Experimental/Predicted Database</b></p>
                            <p>In this page the user can search regulations or see the TRN of a gene or an organism. He/she can also see a brief a statistics overview in the bottom of the page.</p>
                            <ul>
                                <li><p>Search by organism: allows the user to search for all organisms or for a specific organism accordingly with the evidence level (See: Home Page Section).
                                        If a gene is not provided, choosing an organism is mandatory.</p></li>
                                <li><p>Search by gene: allows the user to search for gene id (locus tag from NCBI RefSeq or GenBank version) or gene name.</p></li>
                                <li><p>Search button: search for genomic and regulatory information based on organism and gene options and present it in a table format. If the option ALL is selected a gene is mandatory.</p></li>
                                <li><p>Network Visualization button: selecting an organism is mandatory to activate this button. It draws a dynamic TRN of the selected organism. It is also possible to visualize the regulations of a gene of the selected organism by entering the geneId or gene name in gene option.</p></li>
                                <li><p>Statistics Overview: presents an overview of the database content accordingly with the evidence level (experimental or predicted).</p></li>
                            </ul>
                            <p><b>Search Results Page</b></p>
                            <p>In this page genomic and regulatory information of an organism or gene of interest is presented in a table format. 
                                Some fields contain links to a respective page information or to NCBI (protein id).</p>
                            <p><b>Network Visualization</b></p>
                            <p>This page presents a dynamic TRN of the selected organism or gene. In this page, it is possible to download the regulatory network in a
                                <a href="http://manual.cytoscape.org/en/stable/Supported_Network_File_Formats.html#sif-format" style="color: blue" target="_blank">SIF file format</a> by clicking on the download network
                                file button in the top-left of the screen. Below the download network file button there is a question mark button explaining how to explore the network and two network layout options
                                (Genes and Operons). "Genes" is the default network layout where each gene is presented as an individual node (red for repressor, green for activator, blue for dual regulator, light blue
                                for sigma factor and gray for target gene) and the regulatory interactions are represented by the edges (red for repressions, green for activations, blue for dual regulations, light blue
                                for sigma factors and regulatory interactions with an unknown role). "Operons" is an alternative visualization in which genes that are part of operons that are grouped and represented 
                                as unique nodes in yellow. In both layouts, if the user clicks on the nodes or edges it presents further information about the selected element. Additionally, user is also able to visualize 
                                the regulatory network of that gene (or operon) in a new tab by clicking on the Network Visualization button inside the gene information box. Moving a node is possible when dragging the node to 
                                a desired position. Besides that, the Improve Layout button below network layout options restart the automatic layouting of the network (it is especially useful for big networks),
                                it can be stopped by clicking on the same button (now called “Stop Layouting”).
                            </p>
                            <p><b>Gene Information Page</b></p>
                            <p>In this page genome and regulatory information are presented in the respective tabs. In the binding site prediction tab the user can search for binding sites in the upstream sequence of the gene
                                of interest (Figure 2a) or, if it is a regulator, use its profile HMM to search possible binding sites in other upstream sequences (Figure 2b).</p>
                            <div class="row hmm-image" style="padding-bottom: 30px">
                                <div class="col-sm-6">
                                    <img id="phmm1" class="img-fluid img-responsive" src="images/hmm-partI.png" alt="pHMM">
                                </div>
                                <div class="col-sm-6">
                                    <img id="phmm2" class="img-fluid img-responsive" src="images/hmm-partII.png" alt="pHMM2">
                                </div>
                            </div>
                            <ul>
                                <li><p>a) In the upstream sequence of this gene: it searches for possible binding sites in the upstream sequencing of the gene using the profile HMMs of all or a specific organism</p></li>
                                <li><p>b) For this regulator in other upstream sequences: it searches for possible binding sites in the upstream sequencing of all genes of an organism using the profile HMM of the gene  
                                        of interest in case it is known to code for a TF.</p></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <a class="collapsed card-link" data-toggle="collapse" href="#collapseSeven" style="color: blue">
                        Frequent Navigation Scenarios
                    </a>
                </div>
                <div id="collapseSeven" class="collapse" data-parent="#accordion">
                    <div class="card-body">
                        <p><b>If I have a genome of interest, how can I see genomic and regulatory information? And then check information of a specific regulator? E.g. <i>Corynebacterium glutamicum</i> ATCC 13032 and gene cg2502 (fur).</b></p>
                        <div class="row" style="padding-bottom: 30px">
                            <img class="img-fluid img-responsive" src="images/TF_of_interest.png" alt="TF_of_interest">
                        </div>
                        <p>1. Go to CoryneRegNet home page and click on "Experimental" button (this example), to search for organisms in experimental database, or "Predicted button", to search for organisms in predicted database.
                            If you do not know which one choose, please click on "Predicted" button (experimental + predicted).</p>
                        <p>2. In "Search by organism" choose <i>Corynebacterium glutamicum</i> DSM 20300 = ATCC 13032 and click on "Search" button.</p>
                        <p>3. In the results table, you can see genomic and regulatory information of the selected genome. Scroll down to find cg2502 (fur) in the "Gene ID" column or in the "Regulation" column and click on it.</p>
                        <p>4. The next page will present genomic and regulatory information of the selected gene. The first tab present gene information.</p>
                        <p>5. If you click on "Homologous proteins", it will present found homologous candidates of the selected gene.</p>
                        <p>6. Clicking on "Regulated by" tab it will present the transcription factors that regulates (if any) the selected gene.</p>
                        <p>7. The "Regulates" tab presents all genes that are regulated (if any) by the selected gene.</p>
                        <p>8. The "Gene position and HMM logo" tab presents start and end position of the gene and if the selected gene is a transcription factor it show if it is an auto-regulator, presents the the HMM logo and a
                            button to download the profile HMM.</p>

                        <p><b>If I have a genome of interest, how can I visualize its network in a graph based layout? Can I select a gene of interest from the network and visualize the gene information of it? Or the genes regulated and that regulates this gene in a graph based layout ? E.g. <i>Corynebacterium diphtheriae</i> 31A and gene CD31A_RS034300.</b></p>
                        <div class="row" style="padding-bottom: 30px">
                            <img class="img-fluid img-responsive" src="images/organism_of_interest.png" alt="organism_of_interest">
                        </div>
                        <p>1. Go to CoryneRegNet home page and click on "Predicted" button.</p>
                        <p>2. In search by organism choose <i>Corynebacterium diphtheriae</i> 31A and click on "Network Visualization" button.</p>
                        <p>3. It will automatically draw a dynamic network in a new page. To understand the options of the network visualization page go to "How to navigate" in CoryneRegNet and then to the "Network Visualization" section.</p>
                        <p>4. In the network, scrolling up the mouse makes a zoom in. Zoom in and go to gene (node) CD31A_RS034300 and click on it.</p>
                        <p>5. It opens a modal with some information about the gene and some links.</p>
                        <p>6. Clicking on CD31A_RS034300 opens the gene information page of CD31A_RS034300 in a new tab.</p>
                        <p>7. Clicking on Network Visualization button opens the network with the genes that are regulated by gene CD31A_RS034300 in a new tab. If the selected gene is regulated by other genes it will also appear in the new network.</p>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container-fluid font">
        <div class="footer">
            <div class="row">
                <div class="col-sm-12">
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <span>Developed by: Mariana Parise, Doglas Parise, Josch Pauling, Vasco Azevedo and Jan Baumbach (2020) - CoryneRegNet 7.0</span>
                </div>
            </div>
            <div id="div-footer" class="row">
                <div class="col-sm-2">
                    <a href="https://www.tum.de/" target="_blank" class="center-block">
                        <img src="images/tum-logo.svg" alt="Lights" class="tum-logo">
                    </a>
                    <a href="https://ufmg.br/" target="_blank" class="center-block">
                        <img src="images/logo_ufmg3.png" alt="Lights" class="ufmg-logo">
                    </a>
                </div>
                <div class="col-sm-2">

                </div>                
                <div class="col-sm-8 ">
                    <div class="row text-position">
                        <div class="col-sm-12">
                            <a href="https://www.baumbachlab.net/" target="_blank">
                                <span class="center-block">Experimental Bioinformatics - Baumbach Lab </span>
                            </a>
                        </div>
                    </div>
                    <div class="row text-position">
                        <div class="col-sm-12">
                            <a href="http://www.lgcm.icb.ufmg.br/site/" target="_blank">
                                <span class="center-block">Laboratory of Cellular and Molecular Genetics - LGCM</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    var firefox = navigator.userAgent.indexOf('Firefox') >= 0;
    var chrome = navigator.userAgent.indexOf('Chrome') >= 0;
    var opera = navigator.userAgent.indexOf('Opera') >= 0;
    if (opera == false) {
        opera = navigator.userAgent.indexOf('OPR') >= 0;
    }
    var edge = window.navigator.userAgent.indexOf("Edge") >= 0;

    //console.log("firefox: " + firefox);
    //console.log("chrome: " + chrome);
    //console.log("opera: " + opera);
    //console.log("edge: " + edge);

    if (firefox == false && chrome == false && opera == false && edge == false) {
        var ie = Check_IE_Version();
        console.log("ie: " + ie);
        if (ie < 10) {
            //console.log("Showing message!");
            document.getElementById('browser-messager').style.display = "block";
        }
    }

    function Check_IE_Version() {
        var rv = -1; // Return value assumes failure.

        if (navigator.appName == 'Microsoft Internet Explorer') {

            var ua = navigator.userAgent,
                    re = new RegExp("MSIE ([0-9]{1,}[\\.0-9]{0,})");
            if (re.exec(ua) !== null) {
                rv = parseFloat(RegExp.$1);
            }
        } else if (navigator.appName == "Netscape") {
            /// in IE 11 the navigator.appVersion says 'trident'
            /// in Edge the navigator.appVersion does not say trident
            if (navigator.appVersion.indexOf('Trident') === 1) {
                rv = 11;
            }
        }
        return rv;
    }
</script>
</html>