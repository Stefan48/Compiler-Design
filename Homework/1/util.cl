class Comparator {
    compare(o1: Object, o2: Object): Int {
        0
    };
};

class PriceComparator inherits Comparator {
    compare(o1: Object, o2: Object): Int {
        case o1 of
            prod1: Product =>
            {
                case o2 of
                    prod2: Product =>
                    {
                        let price1: Int <- prod1.getPrice(),
                            price2: Int <- prod2.getPrice()
                        in
                        {
                            if price1 < price2 then 1
                            else if price2 < price1 then 2
                            else 0
                            fi fi;
                        };
                    };
                    obj: Object => { abort(); 0; };
                esac;
            };
            obj: Object => { abort(); 0; };
        esac
    };
};

class RankComparator inherits Comparator {
    compare(o1: Object, o2: Object): Int {
        case o1 of
            r1: Rank =>
            {
                case o2 of
                    r2: Rank =>
                    {
                        let rank1: Int <- r1.getRank(),
                            rank2: Int <- r2.getRank()
                        in
                        {
                            if rank1 < rank2 then 1
                            else if rank2 < rank1 then 2
                            else 0
                            fi fi;
                        };
                    };
                    obj: Object => { abort(); 0; };
                esac;
            };
            obj: Object => { abort(); 0; };
        esac
    };
};

class AlphabeticComparator inherits Comparator {
    compare(o1: Object, o2: Object): Int {
        case o1 of
            str1: String =>
            {
                case o2 of
                    str2: String =>
                    {
                        if str1 < str2 then 1
                        else if str2 < str1 then 2
                        else 0
                        fi fi;
                    };
                    obj: Object => { abort(); 0; };
                esac;
            };
            obj: Object => { abort(); 0; };
        esac
    };
};


class Filter {
    apply(o: Object): Bool {
        true
    };
};

class ProductFilter inherits Filter {
    apply(o: Object): Bool {
        case o of
            prod: Product => true;
            obj: Object => false;
        esac
    };
};

class RankFilter inherits Filter {
    apply(o: Object): Bool {
        case o of
            rank: Rank => true;
            obj: Object => false;
        esac
    };
};

class SamePriceFilter inherits ProductFilter {
    apply(o: Object): Bool {
        case o of
            prod: Product => (prod.getPrice() = prod@Product.getPrice());
            obj: Object => false;
        esac
    };
};


class Tokenizer {
    str: String;
    len: Int;
    start: Int;
    pos: Int;

    init(s: String): SELF_TYPE {{
        str <- s;
        len <- s.length();
        start <- 0;
        pos <- 0;
        self;
    }};

    nextToken(): String {
        let token: String,
            stop: Bool <- (len <= pos)
        in
        {
            while not stop loop
            {
                if str.substr(pos, 1) = " " then
                {
                    token <- str.substr(start, pos-start);
                    start <- pos + 1;
                    stop <- true;
                }
                else
                {
                    if pos = len - 1 then
                    {
                        token <- str.substr(start, pos-start+1);
                        stop <- true;
                    }
                    else false
                    fi;
                }
                fi;
                pos <- pos + 1;

            } pool;
            token;
        }
    };
};


class Converter inherits IO {
    stringToBool(str: String): Bool {
        if str = "true" then
            true
        else if str = "false" then
            false
        else
        {
            abort();
            false;
        }
        fi fi
    };

    charToInt(str: String): Int {
        let len: Int <- str.length()
        in
        {
            if 1 < len then
            {
                abort();
                0;
            }
            else
            {
                if str.substr(0, 1) = "0" then 0
                else if str.substr(0, 1) = "1" then 1
                else if str.substr(0, 1) = "2" then 2
                else if str.substr(0, 1) = "3" then 3
                else if str.substr(0, 1) = "4" then 4
                else if str.substr(0, 1) = "5" then 5
                else if str.substr(0, 1) = "6" then 6
                else if str.substr(0, 1) = "7" then 7
                else if str.substr(0, 1) = "8" then 8
                else if str.substr(0, 1) = "9" then 9
                else
                {
                    abort();
                    0;
                }
                fi fi fi fi fi fi fi fi fi fi;
            }
            fi;
        }
    };

    intToChar(x: Int): String {
        if x = 0 then "0"
        else if x = 1 then "1"
        else if x = 2 then "2"
        else if x = 3 then "3"
        else if x = 4 then "4"
        else if x = 5 then "5"
        else if x = 6 then "6"
        else if x = 7 then "7"
        else if x = 8 then "8"
        else if x = 9 then "9"
        else
        {
            abort();
            "";
        }
        fi fi fi fi fi fi fi fi fi fi
    };

    stringToInt(str: String): Int {
        let len: Int <- str.length(),
            i: Int <- 0,
            res: Int <- 0
        in
        {
            while i < len loop
            {
                res <- res * 10 + charToInt(str.substr(i, 1));
                i <- i + 1;
            } pool;
            res;
        }
    };

    intToString(x: Int): String {
        let res: String,
            pow10: Int <- 1000000000,
            digit: Int
        in
        {
            if x = 0 then
            {
                "0";
            }
            else
            {
                while x < pow10 loop
                {
                    pow10 <- pow10 / 10;
                } pool;
                while 0 < pow10 loop
                {
                    digit <- x / pow10;
                    res <- res.concat(intToChar(digit));
                    x <- x - digit * pow10;
                    pow10 <- pow10 / 10;
                } pool;
                res;
            }
            fi;
        }
    };

    objectToString(obj: Object): String {
        case obj of
            io: IO => "IO()";
            bool: Bool => if bool then "Bool(true)" else "Bool(false)" fi;
            int: Int => "Int(".concat(intToString(int)).concat(")");
            string: String => "String(".concat(string).concat(")");
            product: Product => product.toString();
            rank: Rank => rank.toString();
            object: Object => { abort(); ""; };
        esac
    };
};