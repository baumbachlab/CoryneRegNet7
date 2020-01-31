/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.PromoterRegionDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.PromoterRegion;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class PromoterRegionTest {

    PromoterRegionDAO promoterRegionDAO = new PromoterRegionDAO();

    public static void main(String[] args) {
        
        Gene gene = new Gene();
        GeneDAO geneDAO = new GeneDAO();
        
        GenomeDAO genomeDAO = new GenomeDAO();
        PromoterRegionDAO prDAO = new PromoterRegionDAO();
        //List<PromoterRegion> promoterRegions = prDAO.findByGenome(1318);
      Integer[] ids = {1093141,1093269,1093990,1093880,1093322,1093555,1094114,1094261,1094503,1093402,1093541,1093891,1094115,1094179,1093215,1093226,1093538,
          1093605,1094299,1093497,1094288,1094457,1094363,1093905,1094263,1094252,1093740,1094116,1093671,1093161,1093311,1093610,1094595,1094237,1093431,1094319,
          1094009,1093575,1094419,1093796,1094212,1093737,1093488,1093955,1093334,1093807,1094571,1094190,1093501,1094626,1093487,1094328,1094610,1093255,1094278,
          1093595,1094567,1094201,1094376,1094095,1094556,1093547,1094406,1093963,1094351,1093518,1094541,1093697,1093494,1093834,1093447,1093395,1094039,1094620,
          1094273,1093871,1093722,1093252,1094385,1094628,1094161,1093850,1094045,1094052,1094338,1093410,1094002,1093769,1093174,1094040,1094422,1093725,1094410,
          1094486,1094607,1093757,1093741,1093729,1093681,1093512,1094429,1094361,1093465,1093653,1094308,1093464,1093290,1094166,1093898,1093452,1093635,1093389,1093504,1093387,1093519,1093860,1093651,1094603,1094465,1094447,1093476,1094534,1094230,1093910,1094245,1093981,1094560,1094307,1093543,1093632,1093172,1094331,1094412,1093988,1093210,1093631,1093562,1094617,1094291,1093792,1093544,1093912,1094378,1094066,1094019,1093511,1094037,1093553,1093805,1094016,1094467,1094048,1093918,1093646,1093780,1093874,1093687,1094112,1093197,1094434,1093139,1093169,1093480,1093698,1094096,1094001,1093573,1093373,1093140,1093899,1093382,1093676,1094552,1093591,1093669,1093892,1093238,1093745,1094570,1094229,1094565,1093398,1093917,1093958,1094371,1093680,1093327,1094032,1094065,1094590,1094598,1093999,1094062,1094093,1094209,1094450,1094054,1094315,1094103,1093532,1094244,1093900,1093556,1093477,1093272,1094132,1093237,1093475,1093510,1094109,1093857,1093889,1093564,1093909,1093492,1093969,1094393,1094526,1094383,1093189,1094544,1093915,1094191,1093821,1093928,1094329,1094063,1093426,1094124,1093449,1094547,1093361,1093183,1093944,1094000,1094282,1093812,1094099,1093594,1093359,1093925,1093785,1093859,1093744,1094303,1094101,1093254,1093471,1094089,1093950,1093709,1094018,1093166,1094576,1093317,1093782,1094205,1093728,1094354,1093507,1093278,1093995,1094357,1094453,1094352,1093682,1093643,1093380,1093349,1094098,1093176,1094023,1093250,1093942,1093347,1094417,1094047,1094558,1094452,1094408,1093754,1094369,1093530,1094480,1094584,1094277,1094126,1093672,1094005,1093629,1094014,1093283,1094502,1093746,1094294,1093312,1094067,1093233,1094364,1093895,1093523,1093721,1093435,1094504,1093241,1093837,1094240,1094395,1093848,1093578,1093363,1094613,1094267,1093267,1094489,1094068,1094053,1094469,1094493,1093700,1093295,1093522,1093147,1093579,1094174,1094298,1093211,1093608,1093582,1094582,1093399,1094136,1093970,1094573,1094234,1093302,1093618,1093202,1094314,1093799,1094208,1094254,1094463,1093314,1093444,1094545,1093205,1093320,1093455,1094119,1094436,1094405,1093921,1094168,1093396,1093280,1094113,1094414,1093369,1093371,1094154,1093994,1094572,1093467,1094090,1093719,1093173,1093748,1093265,1093440,1094199,1094134,1094400,1093268,1093462,1094200,1093262,1094388,1093296,1093576,1093342,1094051,1093683,1094524,1094627,1093377,1094484,1094401,1093253,1094231,1093749,1093713,1093679,1093703,1094527,1093152,1094077,1094088,1094260,1093137,1093563,1093599,1094466,1094409,1093408,1093496,1094076,1094475,1093577,1094150,1093592,1094446,1094070,1093890,1093888,1094622,1093663,1093621,1093753,1094509,1093875,1093416,1094589,1093153,1094264,1093481,1093933,1094495,1094156,1094177,1093862,1093802,1093181,1093310,1094444,1094601,1094287,1093619,1093689,1094367,1093927,1093279,1093364,1093870,1093842,1094345,1093580,1094615,1094192,1094220,1093354,1093539,1094506,1094028,1094137,1094337,1094551,1093251,1093814,1093142,1094455,1093485,1094266,1093617,1093156,1094539,1093907,1094477,1094390,1094333,1094416,1094309,1093428,1093902,1094487,1094082,1093940,1093404,1094145,1094155,1094041,1093624,1094472,1093346,1093718,1094604,1093908,1093365,1094290,1093699,1093297,1093450,1094597,1094071,1093438,1093216,1093332,1094448,1094335,1093832,1093201,1093540,1094324,1093660,1094163,1094271,1093824,1093828,1094108,1094144,1094451,1093151,1093755,1094522,1094564,1094542,1093266,1093381,1093783,1093720,1094250,1094217,1094438,1094510,1093456,1093321,1094616,1093886,1094460,1094021,1093470,1093143,1093554,1093484,1093179,1094531,1094125,1093692,1094374,1094578,1094304,1094498,1094384,1094625,1093167,1093498,1093626,1093686,1094213,1093768,1093733,1093919,1094339,1093232,1094214,1093598,1093813,1093340,1093947,1094030,1094210,1094011,1094389,1094044,1093415,1093422,1093225,1093779,1094255,1094055,1094219,1094012,1094435,1093765,1094464,1094020,1093138,1093777,1093263,1093945,1093670,1093960,1093499,1094135,1094105,1093839,1093717,1094593,1094577,1094158,1093170,1094555,1094343,1094392,1094182,1094501,1093520,1093644,1094233,1094042,1094239,1093894,1093454,1094397,1094521,1094195,1094180,1094427,1094172,1094433,1094151,1094362,1093913,1093545,1094049,1093531,1094398,1093922,1093219,1093231,1093801,1094152,1094284,1094516,1093959,1093401,1094175,1093957,1093407,1093901,1094608,1093277,1093560,1094159,1094614,1094575,1093953,1093228,1093967,1093649,1093235,1094296,1093869,1094196,1094396,1093833,1094148,1094027,1093442,1094494,1093337,1094265,1093685,1093526,1094322,1093270,1093639,1094029,1093603,1094471,1093336,1093811,1093309,1093866,1093341,1094301,1093264,1094618,1093716,1093701,1093491,1094391,1094247,1094425,1093684,1094270,1094325,1093165,1093843,1094111,1094443,1094476,1094520,1093207,1093506,1093324,1093362,1093146,1093673,1094149,1094292,1093731,1093461,1094215,1093759,1094188,1093460,1093916,1093319,1093642,1093348,1093193,1093230,1093411,1093774,1094197,1093536,1093742,1093570,1093229,1094320,1093291,1094370,1093298,1094310,1094243,1093935,1093647,1094058,1094187,1093529,1093645,1093568,1093409,1094528,1094075,1094449,1093987,1093982,1093244,1094216,1094358,1093623,1093694,1093596,1094046,1094022,1093690,1093206,1094248,1093914,1093976,1093150,1093864,1093878,1094226,1093989,1094222,1094353,1094365,1093178,1093736,1094402,1093865,1093393,1094313,1093734,1093305,1093614,1093829,1093525,1093419,1093868,1094050,1094276,1094441,1093425,1093565,1094431,1093474,1093271,1093893,1094272,1094403,1094183,1093904,1094194,1094034,1093457,1093654,1093417,1093284,1094619,1094256,1093551,1094458,1093688,1093569,1094439,1094507,1093533,1094479,1094073,1093712,1093808,1093390,1093374,1093500,1094505,1093187,1093421,1093881,1094566,1093413,1093292,1093535,1093424,1093326,1093983,1093429,1093360,1093287,1094006,1093867,1093188,1094316,1094549,1093182,1093433,1094225,1094580,1094122,1093286,1093224,1094036,1094207,1093836,1093723,1093315,1093872,1094336,1093323,1093597,1093586,1093490,1093191,1094581,1094221,1094289,1093397,1093965,1094609,1093877,1093521,1094204,1093441,1093448,1094181,1093423,1094285,1094360,1093674,1093160,1094094,1093330,1093622,1094591,1094559,1093427,1093537,1093256,1093903,1093236,1093313,1093199,1093634,1094121,1093667,1094164,1093527,1093705,1094483,1094010,1093628,1094160,1094355,1094500,1093282,1093208,1093383,1093972,1094003,1093483,1093301,1094141,1094227,1094415,1093593,1093248,1094128,1093388,1093559,1094280,1093261,1093325,1093552,1093259,1093711,1093977,1094083,1093273,1093798,1094056,1094157,1093787,1093876,1094038,1093185,1093923,1093637,1094242,1093861,1094606,1093941,1093394,1093240,1094428,1093788,1093666,1093800,1094525,1093331,1094007,1093760,1094061,1093964,1093991,1094514,1093513,1094097,1093974,1093961,1094356,1093567,1093820,1094456,1093307,1093162,1093602,1093966,1094621,1093405,1093992,1094413,1094033,1093581,1094085,1093677,1094293,1093606,1094203,1094554,1094399,1094612,1093625,1093818,1094269,1094478,1093214,1094170,1094176,1093853,1093968,1094421,1094249,1093293,1094349,1094262,1093379,1093285,1094153,1094236,1094211,1094485,1093446,1093661,1093344,1094146,1094512,1093668,1094550,1094440,1093246,1093502,1094258,1093514,1093196,1094535,1093378,1094379,1093751,1094348,1093589,1094562,1094583,1093856,1094424,1094133,1093790,1094340,1093275,1094131,1094015,1094102,1094311,1093604,1093384,1094246,1093218,1094423,1093998,1094368,1093962,1093735,1094232,1094462,1094024,1093468,1094482,1093840,1093896,1094138,1093730,1094072,1093308,1094326,1093479,1093574,1093600,1093819,1094297,1093929,1094228,1093356,1093984,1093583,1094532,1094295,1093432,1093572,1093157,1094069,1094530,1093627,1094470,1093281,1094025,1094488,1093542,1093472,1093548,1093630,1094600,1093303,1093727,1094078,1093375,1093657,1094162,1093854,1093883,1093376,1093827,1093979,1093835,1093222,1094540,1093466,1093607,1093887,1093776,1094342,1093412,1094184,1093996,1094327,1094173,1094004,1093339,1094373,1094259,1093758,1094206,1094086,1093482,1093873,1093392,1093159,1093294,1093195,1094461,1094517,1093938,1093849,1094513,1093789,1093825,1094193,1093350,1094117,1093973,1093247,1093926,1093851,1093724,1094081,1094394,1094536,1093318,1093897,1093549,1093198,1093489,1094031,1093665,1093306,1093333,1093817,1093948,1094283,1093946,1094418,1093884,1094623,1093980,1093951,1093786,1093678,1093997,1093459,1093588,1093636,1093372,1093220,1093882,1094091,1094189,1094274,1093863,1093934,1093795,1093245,1094218,1093486,1093355,1093664,1093810,1094318,1093516,1094519,1093524,1094579,1094511,1094241,1093756,1094100,1093986,1093505,1094624,1093772,1093258,1093203,1094104,1094499,1094118,1093227,1093706,1094508,1094167,1094605,1093707,1094372,1093770,1094445,1094079,1093517,1093434,1093352,1094110,1094490,1093353,1093726,1094306,1094087,1093831,1093260,1093738,1093328,1094468,1094169,1094257,1093784,1093815,1094377,1094341,1094497,1094382,1094251,1093550,1094586,1094084,1093816,1093710,1094611,1093794,1094106,1093612,1094454,1094026,1093587,1094411,1093752,1093385,1093620,1094491,1094268,1093715,1093806,1094235,1093200,1094080,1094279,1093763,1094107,1093855,1093445,1094223,1094518,1093163,1093648,1093847,1094529,1093430,1093809,1093615,1093335,1093217,1093767,1093747,1094574,1094092,
          1093145,1093437,1094171,1094129,1093403,1093985,1093144,1094143,1094043,1094474,1094238,1093773,1093534,1094375,1093508,1093993,1093791,1093528,1094543,1093797,1094013,1094473,1093148,1093155,1094599,1093190,1094185,1094442,1094553,1093168,1094587,1094426,1093343,1094492,1093439,1093358,1093846,1093257,1094588,1093739,1094347,1094386,1093158,1093223,1093590,1094224,1093732,1094563,1093495,1093192,1093609,1093180,1093803,1093924,1094060,1094275,1093932,1093939,1093743,1093243,1093943,1093640,1093764,1094430,1093478,1094380,1094008,1093546,1093611,1094420,1094387,1093775,1093822,1093633,1093708,1093239,1094533,1093584,1094334,1093418,1094404,1094561,1093830,1094359,1093675,1093852,1093503,1094300,1093879,1093906,1094538,1093242,1093289,1093936,1093558,1094321,1093453,1093213,1094407,1093585,1094594,1094057,1093436,1094381,1093954,1094557,1093778,1093911,1093154,1094198,1093515,1094596,1093370,1094140,1094323,1093616,1094592,1093804,1093838,1093458,1094344,1094178,1093845,1093366,1094147,1093920,1094312,1093171,1093276,1093641,1093931,1093204,1094165,1093463,1093650,1094548,1093704,1093638,1093149,1094437,1093762,1093695,1094537,1093368,1093937,1093949,1093652,1093557,1094142,1093367,1093885,1093391,1094332,1093451,1094317,1093930,1093493,1093469,1093696,1093978,1093186,1094569,1093221,1093338,1094123,1093781,1093601,1093655,1094515,1094253,1093164,1093400,1093975,1093473,1093212,1093662,1093844,1093771,1093750,1094496,1093956,1093952,1093351,1094139,1093386,1093826,1094302,1093300,1094281,1093175,1094035,1094366,1093561,1093177,1094432,1094481,1094186,1093658,1094585,1094127,1093571,1093761,1093509,1093299,1093841,1093194,1094350,1094602,1093702,1094074,1094120,1094286,1093249,1094202,1093209,1093443,1093184,1094305,1093288,1093234,1093345,1093714,1093566,1093329,1094568,1094346,1093420,1093691,1093316,1094546,1093656,1093858,1094064,1093823,1093613,1093304,1094523,1093414,1093274,1093659,1093971,1093693,1093766,1094017,1094059,1094330,1093793,1094459,1093357,1094130,1093406};
      
        for (Integer id : ids) {
            PromoterRegion promoterRegion = prDAO.findById(id);
            System.out.println(promoterRegion.toString());
            prDAO.delete(promoterRegion);
        }
    
    
    
    }

    public void save(PromoterRegion promoterRegion) {
        promoterRegionDAO = new PromoterRegionDAO();

        try {
            promoterRegionDAO.save(promoterRegion);
        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public PromoterRegion findById(Integer id) {
        promoterRegionDAO = new PromoterRegionDAO();
        PromoterRegion promoterRegion = promoterRegionDAO.findById(id);
        return promoterRegion;
    }

    public void update(PromoterRegion promoterRegion) {
        promoterRegionDAO = new PromoterRegionDAO();
        promoterRegionDAO.update(promoterRegion);
    }

    public void listAll() {
        promoterRegionDAO = new PromoterRegionDAO();
        List<PromoterRegion> promoterRegionrics = promoterRegionDAO.listAll();
        for (PromoterRegion promoterRegionric : promoterRegionrics) {
            System.out.println(promoterRegionric.toString());
        }

    }

    public void delete(PromoterRegion promoterRegion) {
        promoterRegionDAO = new PromoterRegionDAO();
        promoterRegionDAO.delete(promoterRegion);
    }

}