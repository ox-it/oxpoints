#!/usr/bin/perl -w
use strict;
#<obn>375</obn><placeName>Wolfson College Annexe</placeName><construction>1960</construction>
#<obn>541</obn><placeName>2 Worcester Street</placeName><construction>1988</construction><acquisition>2009</acquisition>

my %lines;
my %found;
while (<>) { 
  my $obn = "";
  my $placeName = "";
  my $constructionYear = "";
  my $acquisitionYear = "";
  if (m|<obn>([0-9]+)</obn><placeName>([^<]+)</placeName>\n$|) {
    $obn = $1;
    $placeName = $2;
  } elsif (m|<obn>([0-9]+)</obn><placeName>([^<]+)</placeName><construction>([0-9]+)</construction>\n$|) { 
    $obn = $1;
    $placeName = $2;
    $constructionYear = $3;
  } elsif (m|<obn>([0-9]+)</obn><placeName>([^<]+)</placeName><construction>([0-9]+)</construction><acquisition>([0-9]+)</acquisition>\n$|) { 
    $obn = $1;
    $placeName = $2;
    $constructionYear = $3;
    $acquisitionYear = $4;
  } else { die "Unrecognised line $_"; }
  $lines{$placeName} = $_;
}

open FILE, "<", "oxpoints_plus.xml" or die $!;
my $c = 0;
while(<FILE>) {
  searchLine($_);
}
close FILE;

my $unique = 0;
foreach my $key (keys %found) {
  $unique++;
  if ($found{$key} > 1) { 
    print "$found{$key} $lines{$key}\n";
  }
}

sub searchLine {
  my $line = shift;
  my $key;
  foreach $key (keys %lines) {
    if ($line =~ /$key/) {
      $found{$key}++;
      $c++;
      print "$line";
    } 
  }
}

print "Found $c\n";
print "Unique $unique\n";

