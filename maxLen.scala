var maxLen = 0

for ( w <- words ) {
	if ( w.length > maxLen ) {
		maxLen = w.length
	}
}

println( maxLen )