use strict;
use warnings;
my ($sec,$min,$hour,$mday,$mon,$year,$wday,$yday,$isdst)=localtime(time); #Breaks up standard timestamp
my $nice_timestamp = sprintf("%04d%02d%02d",$year+1900,$mon+1,$mday); #Creates new timestamp
my $timer = sprintf("%02d:%02d:%02d",$hour,$min,$sec);
my $filename = "/home/jacksonsippe/DailyLogs/InternetLog$nice_timestamp"; #Makes filename with timestamp
open(my $fh, '>>', $filename) or die "Could not open file '$filename' $!"; #Creates file
my @output = grep {/^((Up|Down)load|km]):/} qx"/home/jacksonsippe/.local/bin/speedtest-cli"; #Runs the speedtest program and saves output full path needed for crontab
print $fh "Time: ";
print $fh $timer;
print $fh "\n";
print $fh $_ for @output; #Prints output
print $fh "\n\n";
close $fh; #Closes file
