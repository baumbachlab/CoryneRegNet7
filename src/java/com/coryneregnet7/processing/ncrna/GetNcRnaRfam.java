/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.ncrna;

/**
 *
 * @author mariana
 */
public class GetNcRnaRfam {
    
    /*
    mysql --user rfamro --host mysql-rfam-public.ebi.ac.uk --port 4497 --database Rfam

mysql> SELECT fr.rfam_acc, fr.rfamseq_acc, fr.seq_start, fr.seq_end, f.type
    FROM full_region fr, rfamseq rf, taxonomy tx, family f
    WHERE
    rf.ncbi_id = tx.ncbi_id
    AND f.rfam_acc = fr.rfam_acc
            AND fr.rfamseq_acc = rf.rfamseq_acc
    AND tx.tax_string LIKE '%Corynebacterium%'
    AND f.type LIKE '%sRNA%'
    AND is_significant = 1;

    */
    
    /*
    +----------+----------------+-----------+---------+-------------+
| rfam_acc | rfamseq_acc    | seq_start | seq_end | type        |
+----------+----------------+-----------+---------+-------------+
| RF02469  | FXAR01000001.1 |    190343 |  190438 | Gene; sRNA; |
| RF02469  | CP007790.1     |   2081205 | 2081321 | Gene; sRNA; |
| RF02469  | CP007792.1     |     95977 |   96093 | Gene; sRNA; |
| RF02469  | CP007790.1     |   1943873 | 1943989 | Gene; sRNA; |
| RF02469  | CP003697.1     |    768367 |  768484 | Gene; sRNA; |
| RF02469  | CP011545.1     |   1405586 | 1405470 | Gene; sRNA; |
| RF02469  | BA000035.2     |   1558641 | 1558759 | Gene; sRNA; |
| RF02469  | CAFW01000025.1 |     44348 |   44232 | Gene; sRNA; |
| RF02469  | CP006764.1     |    181417 |  181533 | Gene; sRNA; |
| RF02469  | BA000035.2     |    273092 |  273208 | Gene; sRNA; |
| RF02469  | BA000035.2     |    403763 |  403647 | Gene; sRNA; |
| RF02469  | BA000035.2     |    117420 |  117536 | Gene; sRNA; |
| RF02469  | BA000035.2     |    377957 |  378073 | Gene; sRNA; |
| RF02469  | CP007790.1     |   1467238 | 1467354 | Gene; sRNA; |
| RF02469  | BA000035.2     |    448056 |  447940 | Gene; sRNA; |
| RF02469  | CP004352.1     |     14442 |   14558 | Gene; sRNA; |
| RF02469  | CP011545.1     |    910113 |  910229 | Gene; sRNA; |
| RF02514  | CP011312.1     |    132448 |  132738 | Gene; sRNA; |
| RF02514  | CP004353.1     |    225979 |  226262 | Gene; sRNA; |
| RF02514  | BA000035.2     |   1056645 | 1056348 | Gene; sRNA; |
| RF02514  | CP002917.1     |   2923302 | 2923441 | Gene; sRNA; |
| RF02514  | CP008944.1     |   1988568 | 1988860 | Gene; sRNA; |
| RF02514  | KI515717.1     |    714157 |  713868 | Gene; sRNA; |
| RF02514  | CP003924.1     |   2524287 | 2524004 | Gene; sRNA; |
| RF02514  | CP001829.1     |   2101959 | 2102245 | Gene; sRNA; |
| RF02514  | LWNU01000002.1 |     32760 |   32471 | Gene; sRNA; |
| RF02514  | KB290831.1     |    715727 |  715439 | Gene; sRNA; |
| RF02514  | CP006842.1     |    447313 |  447012 | Gene; sRNA; |
| RF02514  | CP011546.1     |    590130 |  589844 | Gene; sRNA; |
| RF02514  | CP009215.1     |    509684 |  509397 | Gene; sRNA; |
| RF02514  | CP009248.1     |   1567968 | 1567671 | Gene; sRNA; |
| RF02514  | CP009249.1     |    582288 |  582003 | Gene; sRNA; |
| RF02514  | CP007156.1     |   2448505 | 2448791 | Gene; sRNA; |
| RF02514  | CP008913.1     |   1610546 | 1610848 | Gene; sRNA; |
| RF02514  | BA000036.3     |     92436 |   92144 | Gene; sRNA; |
| RF02514  | GG667038.1     |    277075 |  276786 | Gene; sRNA; |
| RF02514  | CP004354.1     |    940148 |  939869 | Gene; sRNA; |
| RF02514  | FTOF01000012.1 |     14598 |   14887 | Gene; sRNA; |
| RF02514  | LKEV01000001.1 |    378715 |  378420 | Gene; sRNA; |
| RF02514  | CP012342.1     |   1374171 | 1374457 | Gene; sRNA; |
| RF02514  | CP001620.1     |    951315 |  951614 | Gene; sRNA; |
| RF02514  | CP003697.1     |   2124634 | 2124346 | Gene; sRNA; |
| RF02514  | AM942444.1     |   2059961 | 2059672 | Gene; sRNA; |
| RF02514  | GG667192.1     |    372763 |  372471 | Gene; sRNA; |
| RF02734  | BA000036.3     |     10053 |    9921 | Gene; sRNA; |
| RF02734  | BA000036.3     |     10053 |    9921 | Gene; sRNA; |
| RF02734  | AP009044.1     |     10054 |    9921 | Gene; sRNA; |
+----------+----------------+-----------+---------+-------------+
47 rows in set (52.74 sec)

    */
    
}
