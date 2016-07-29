#-------------------------------------------------------------------------------
# Project Name  : ISP
# Written by    : Gilberto Perez
# Last Modified : 04/18/2014
#-------------------------------------------------------------------------------


# Notice the '.PHONY' target. 
# This target tells Make that 'all' and 'clean' are not files. This is usually
# not required, but useful if for some reason
.PHONY: clean all tarball

all: isp

# The clean target should be used before you create your tarball to remove the
# binaries created during compilation
clean:
	rm -f isp
	rm -f *.o
	rm -fr input
	rm -fr output

# The following targets automate the process of creating a tarball. To activate
# it simply remove the leading '# ' and replace '<username>' with your own.
 tarball: dbrana-isp.tar.gz
#
  dbrana-isp.tar.gz: clean
		cd .. &&	tar -czvf dbrana-isp.tar.gz ./isp/*

# These targets take care of the compilation process.
isp: isp.o
	g++ isp.o -o isp

isp.o: isp.cpp
	g++ -c isp.cpp
