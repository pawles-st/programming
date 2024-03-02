#ifndef FIGURA_H
#define FIGURA_H

class Figura {
	public:
		virtual float calcArea()=0;
		virtual float calcCircumference()=0;
		virtual ~Figura()=default;
};

class Czworokat : public Figura {
	protected:
		Czworokat(float b1, float b2, float b3, float b4, float k) : bok1(b1), bok2(b2), bok3(b3), bok4(b4), kat(k){}
		float bok1, bok2, bok3, bok4;
		float kat;
};

class Kolo : public Figura {
	private:
		float promien;
	public:
		Kolo(float p) : promien(p){}
		float calcArea();
		float calcCircumference();
};

class Kwadrat : public Czworokat {
	public:
		Kwadrat(float b1, float b2, float b3, float b4, float k) : Czworokat(b1, b2, b3, b4, k){}
		float calcArea();
		float calcCircumference();
};

class Pieciokat : public Figura {
	private:
		float bok;
	public:
		Pieciokat(float b) : bok(b){}
		float calcArea();
		float calcCircumference();
};

class Prostokat : public Czworokat {
	public:
		Prostokat(float b1, float b2, float b3, float b4, float k) : Czworokat(b1, b2, b3, b4, k){}
		float calcArea();
		float calcCircumference();
};

class Romb : public Czworokat {
	public:
		Romb(float b1, float b2, float b3, float b4, float k) : Czworokat(b1, b2, b3, b4, k){}
		float calcArea();
		float calcCircumference();
};

class Szesciokat : public Figura {
	private:
		float bok;
	public:
		Szesciokat(float b) : bok(b){}
		float calcArea();
		float calcCircumference();
};

#endif
