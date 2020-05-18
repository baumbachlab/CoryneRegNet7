touch *.ps
for i in *.ps; 
do 
  ##RNAz $i > $i.rnaz; 
  filename=$i".png";
  mod=${i//ps/png}
  echo $i;
  echo $mod
  #path="/data/home/mariana/Downloads/RNAdetect-master-37a/RNAdetect-master/RNAdetect/bin/"
##  if [ ! -f "$filename" ];
##    then
##     echo ".rnaz nao existe"
     convert -density 300 $i $mod;
#    $path"RNAdetect" output-cgb_33976.fa.sorted > output-cgb_33976.fa.sorted.rnadetect;
##  fi
done
