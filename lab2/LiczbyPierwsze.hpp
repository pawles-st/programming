#ifndef LICZBYPIERWSZE_HPP
#define LICZBYPIERWSZE_HPP

class LiczbyPierwsze {
	private:
		int* primes;
		int p_size;
	public:
		LiczbyPierwsze(int n);
		~LiczbyPierwsze();
		int liczba(int m);
};

#endif
