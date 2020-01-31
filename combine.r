#!/usr/bin/env Rscript
args = commandArgs(trailingOnly=TRUE)

library(metap)
minimump(c(as.double(args[1]), as.double(args[2]), as.double(args[3])))

