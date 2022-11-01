class List inherits IO {
    size: Int;

    isEmpty(): Bool {
        true
    };

    head(): Object {{
        abort();
        self;
    }};

    tail(): List {{
        abort();
        self;
    }};

    last(): Object {{
        abort();
        self;
    }};

    cons(object: Object): NonemptyList {
        new NonemptyList.init(object, self)
    };

    size(): Int {
        size
    };

    toStringAux(): String {
        ""
    };

    toString(): String {
        "[  ]"
    };

    merge(other: List): List {
        other
    };

    filterBy(filter: Filter): List {
        self
    };

    insert(obj: Object, comparator: Comparator, ascendent: Bool): NonemptyList {
        self.cons(obj)
    };

    sortBy(comparator: Comparator, ascendent: Bool): List {
        self
    };
};

class NonemptyList inherits List {
    head: Object;
    tail: List;

    init(h: Object, t: List): SELF_TYPE {{
        head <- h;
        tail <- t;
        size <- 1 + tail.size();
        self;
    }};

    isEmpty(): Bool {
        false
    };

    head(): Object {
        head
    };

    tail(): List {
        tail
    };

    last(): Object {
        if tail.isEmpty() then
            head
        else
            tail.last()
        fi
    };

    cons(obj: Object): NonemptyList {
        new NonemptyList.init(obj, self)
    };

    toStringAux(): String {
        let converter: Converter <- new Converter
        in
        {
            if size = 1 then
                converter.objectToString(head)
            else
                tail.toStringAux().concat(", ").concat(converter.objectToString(head))
            fi;
        }
    };

    toString(): String {
        let converter: Converter <- new Converter
        in
        {
            "[ ".concat(toStringAux()).concat(" ]");
        }
    };

    merge(other: List): List {
        if other.isEmpty() then
        {
            self;
        }
        else
        {
            merge(other.tail()).cons(other.head());
        }
        fi
    };

    filterBy(filter: Filter): List {
        if filter.apply(head) then
        {
            tail.filterBy(filter).cons(head);
        }
        else
        {
            tail.filterBy(filter);
        }
        fi
    };

    insert(obj: Object, comparator: Comparator, ascendent: Bool): NonemptyList {
        let value: Int <- comparator.compare(obj, head)
        in
        {
            if ascendent then
            {
                if value < 2 then
                {
                    tail.insert(obj, comparator, ascendent).cons(head);
                }
                else
                {
                    self.cons(obj);
                }
                fi;
            }
            else
            {
                if value < 2 then
                {
                    self.cons(obj);
                }
                else
                {
                    tail.insert(obj, comparator, ascendent).cons(head);
                }
                fi;
            }
            fi;
        }
    };

    sortBy(comparator: Comparator, ascendent: Bool): List {
        tail.sortBy(comparator, ascendent).insert(head, comparator, ascendent)
    };
};