#!/usr/bin/perl -w
use strict;


#"Building No","Building Name","Construction/PC Year","Acquisition Year"
#101,"DARTINGTON HOUSE ",1970,1970
{

my $lineNo;
my ($bn, $desc, $y1, $y2); 

$lineNo= 0;
while(<>) { 
	if ($lineNo > 0) {
		if (m/^([0-9]+),"([^"]+)",([0-9]*),([0-9]*)\n$/) { 
			$bn=$1;
			$desc=camelCaps($2);
      $desc = hack($desc);
			$y1=$3;
			$y2=$4;
			
      print "<obn>$bn</obn>";
	    print "<placeName>$desc</placeName>";
			if ($y1 ne "") { 
				print "<construction>$y1</construction>";
			}
			if ($y2 ne "") { 
				print "<acquisition>$y2</acquisition>";
			}
			print "\n";
			
		} else { 
			die "Unrecognised line: $_";
		}
	}
	$lineNo++;
}
}
sub camelCaps { 
	my $s = shift;
	my $ret = "";
	my $w;
	my @words = split(/ /, $s);
	foreach $w (@words) { 
		$w = lc($w);
		$w =~ s/^(\S)/\U$1\E/;
		$ret = $ret . " ";
    $ret = $ret . $w;
	}
  $ret =~ s/^ //;
	return $ret;
}

sub hack {
	my $s = shift;
  $s =~ s/ Sq / Square /;
  $s =~ s/ Sq^/ Square /;
  $s =~ s/ Rd / Road /;
  $s =~ s/ Rd^/ Road/;
  $s =~ s/\&/and/;
  $s =~ s/\((\S)/\(\U$1\E/;
  return $s;  
}
