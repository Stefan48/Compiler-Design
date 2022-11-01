class Main inherits IO {
    lists: List;

    printHelp(): Object {{
        out_string("> help\n");
        out_string("> load\ntype attr1 attr2 ...\n...\ntype attr1 attr2 ...\nEND\n");
        out_string("> print [index]\n");
        out_string("> merge index1 index2\n");
        out_string("> filterBy index {ProductFilter, RankFilter, SamePriceFilter}\n");
        out_string("> sortBy index {PriceComparator, RankComparator, AlphabeticComparator} {ascendent, descendent}\n");
    }};

    loadObjects(): Object {
        let stop: Bool <- false,
            line: String,
            tokenizer: Tokenizer,
            type: String,
            arg1: String,
            arg2: String,
            arg3: String,
            list: List <- new List,
            converter: Converter <- new Converter
        in
        {
            while not stop loop
            {
                line <- in_string();
                tokenizer <- new Tokenizer.init(line);
                type <- tokenizer.nextToken();
                if type = "END" then
                {
                    stop <- true;
                }
                else if type = "IO" then
                {
                    list <- list.cons(new IO);
                }
                else if type = "Bool" then
                {
                    arg1 <- tokenizer.nextToken();
                    list <- list.cons(converter.stringToBool(arg1));
                }
                else if type = "Int" then
                {
                    arg1 <- tokenizer.nextToken();
                    list <- list.cons(converter.stringToInt(arg1));
                }
                else if type = "String" then
                {
                    arg1 <- tokenizer.nextToken();
                    list <- list.cons(arg1);
                }
                else if type = "Soda" then
                {
                    arg1 <- tokenizer.nextToken();
                    arg2 <- tokenizer.nextToken();
                    arg3 <- tokenizer.nextToken();
                    list <- list.cons(new Soda.init(arg1, arg2, converter.stringToInt(arg3)));
                }
                else if type = "Coffee" then
                {
                    arg1 <- tokenizer.nextToken();
                    arg2 <- tokenizer.nextToken();
                    arg3 <- tokenizer.nextToken();
                    list <- list.cons(new Coffee.init(arg1, arg2, converter.stringToInt(arg3)));
                }
                else if type = "Laptop" then
                {
                    arg1 <- tokenizer.nextToken();
                    arg2 <- tokenizer.nextToken();
                    arg3 <- tokenizer.nextToken();
                    list <- list.cons(new Laptop.init(arg1, arg2, converter.stringToInt(arg3)));
                }
                else if type = "Router" then
                {
                    arg1 <- tokenizer.nextToken();
                    arg2 <- tokenizer.nextToken();
                    arg3 <- tokenizer.nextToken();
                    list <- list.cons(new Router.init(arg1, arg2, converter.stringToInt(arg3)));
                }
                else if type = "Private" then
                {
                    arg1 <- tokenizer.nextToken();
                    list <- list.cons(new Private.init(arg1));
                }
                else if type = "Corporal" then
                {
                    arg1 <- tokenizer.nextToken();
                    list <- list.cons(new Corporal.init(arg1));
                }
                else if type = "Sergent" then
                {
                    arg1 <- tokenizer.nextToken();
                    list <- list.cons(new Sergent.init(arg1));
                }
                else if type = "Officer" then
                {
                    arg1 <- tokenizer.nextToken();
                    list <- list.cons(new Officer.init(arg1));
                }
                else
                {
                    abort();
                }
                fi fi fi fi fi fi fi fi fi fi fi fi fi;
            } pool;
            lists <- lists.cons(list);
        }
    };

    printAllLists(l: List): Object {
        if not l.isEmpty() then
        {
            printAllLists(l.tail());
            out_int(l.size());
            out_string(": ");
            case l.head() of
                currentList: List => out_string(currentList.toString());
                obj: Object => abort();
            esac;
            out_string("\n");
        }
        else false
        fi
    };

    printListWithIndex(index: Int): Object {
        let l: List <- lists (* shallow copy necessary ??? *)
        in
        {
            while index < l.size() loop
            {
                l <- l.tail();
            } pool;
            case l.head() of
                listToPrint: List => out_string(listToPrint.toString().concat("\n"));
                obj: Object => abort();
            esac;
        }
    };

    printLists(index: Int): Object {{
        if index = 0 then
        {
            printAllLists(lists);
        }
        else
        {
            if lists.size() < index then
            {
                abort();
            }
            else
            {
                printListWithIndex(index);
            }
            fi;
        }
        fi;
    }};

    removeLists(l: List, index1: Int, index2: Int): List {
        if l.isEmpty() then
        {
            l;
        }
        else
        {
            if l.size() = index1 then
            {
                removeLists(l.tail(), index1, index2);
            }
            else
            {
                if l.size() = index2 then
                {
                    removeLists(l.tail(), index1, index2);
                }
                else
                {
                    removeLists(l.tail(), index1, index2).cons(l.head());
                }
                fi;
            }
            fi;
        }
        fi
    };

    mergeLists(index1: Int, index2: Int): Object {
        let list1: List,
            list2: List,
            l: List,
            stop: Bool <- false
        in
        {
            if lists.size() < index1 then
            {
                abort();
            }
            else
            {
                if lists.size() < index2 then
                {
                    abort();
                }
                else false
                fi;
            }
            fi;
            l <- lists;
            while not stop loop
            {
                if l.size() = index1 then
                {
                    case l.head() of
                        firstList: List => list1 <- firstList;
                        obj: Object => abort();
                    esac;
                }
                else
                {
                    if l.size() = index2 then
                    {
                        case l.head() of
                            secondList: List => list2 <- secondList;
                            obj: Object => abort();
                        esac;
                    }
                    else false
                    fi;
                }
                fi;
                l <- l.tail();
                if l.isEmpty() then
                {
                    stop <- true;
                }
                else
                {
                    if not isvoid list1 then
                    {
                        if not isvoid list2 then
                        {
                            stop <- true;
                        }
                        else false
                        fi;
                    }
                    else false
                    fi;
                }
                fi;
            } pool;
            lists <- removeLists(lists, index1, index2);
            lists <- lists.cons(list1.merge(list2));
        }
    };

    filterLists(l: List, index: Int, filter: Filter): List {
        if l.size() < index then
        {
            abort();
            l;
        }
        else
        {
            if l.isEmpty() then
            {
                l;
            }
            else
            {
                if l.size() = index then
                {
                    case l.head() of
                        listToFilter: List => l.tail().cons(listToFilter.filterBy(filter));
                        obj: Object => { abort(); l; };
                    esac;
                }
                else
                {
                    filterLists(l.tail(), index, filter).cons(l.head());
                }
                fi;
            }
            fi;
        }
        fi
    };

    sortLists(l: List, index: Int, comparator: Comparator, ascendent: Bool): List {
        if l.size() < index then
        {
            abort();
            l;
        }
        else
        {
            if l.isEmpty() then
            {
                l;
            }
            else
            {
                if l.size() = index then
                {
                    case l.head() of
                        listToSort: List => l.tail().cons(listToSort.sortBy(comparator, ascendent));
                        obj: Object => { abort(); l; };
                    esac;
                }
                else
                {
                    sortLists(l.tail(), index, comparator, ascendent).cons(l.head());
                }
                fi;
            }
            fi;
        }
        fi
    };

    main(): Object {
        let command: String,
            cmd: String,
            tokenizer: Tokenizer,
            converter: Converter <- new Converter,
            arg1: String,
            arg2: String,
            arg3: String,
            index: Int,
            ascendent: Bool
        in
        {
            lists <- new List;
            loadObjects();
            while true loop
            {
                command <- in_string();
                tokenizer <- new Tokenizer.init(command);
                cmd <- tokenizer.nextToken();
                if cmd = "help" then
                {
                    printHelp();
                }
                else if cmd = "load" then
                {
                    loadObjects();
                }
                else if cmd = "print" then
                {
                    arg1 <- tokenizer.nextToken();
                    if arg1 = "" then
                    {
                        printLists(0);
                    }
                    else
                    {
                        index <- converter.stringToInt(arg1);
                        if index = 0 then
                        {
                            abort();
                        }
                        else
                        {
                            printLists(index);
                        }
                        fi;
                    }
                    fi;
                }
                else if cmd = "merge" then
                {
                    arg1 <- tokenizer.nextToken();
                    arg2 <- tokenizer.nextToken();
                    mergeLists(converter.stringToInt(arg1), converter.stringToInt(arg2));
                }
                else if cmd = "filterBy" then
                {
                    arg1 <- tokenizer.nextToken();
                    index <- converter.stringToInt(arg1);
                    arg2 <- tokenizer.nextToken();
                    if arg2 = "ProductFilter" then
                    {
                        lists <- filterLists(lists, index, new ProductFilter);
                    }
                    else if arg2 = "RankFilter" then
                    {
                        lists <- filterLists(lists, index, new RankFilter);
                    }
                    else if arg2 = "SamePriceFilter" then
                    {
                        lists <- filterLists(lists, index, new SamePriceFilter);
                    }
                    else
                    {
                        abort();
                    }
                    fi fi fi;
                }
                else if cmd = "sortBy" then
                {
                    arg1 <- tokenizer.nextToken();
                    index <- converter.stringToInt(arg1);
                    arg2 <- tokenizer.nextToken();
                    arg3 <- tokenizer.nextToken();
                    if arg3 = "ascendent" then
                    {
                        ascendent <- true;
                    }
                    else if arg3 = "descendent" then
                    {
                        ascendent <- false;
                    }
                    else
                    {
                        abort();
                    }
                    fi fi;
                    if arg2 = "PriceComparator" then
                    {
                        lists <- sortLists(lists, index, new PriceComparator, ascendent);
                    }
                    else if arg2 = "RankComparator" then
                    {
                        lists <- sortLists(lists, index, new RankComparator, ascendent);
                    }
                    else if arg2 = "AlphabeticComparator" then
                    {
                        lists <- sortLists(lists, index, new AlphabeticComparator, ascendent);
                    }
                    else
                    {
                        abort();
                    }
                    fi fi fi;
                }
                else
                {
                    abort();
                }
                fi fi fi fi fi fi;
            } pool;
        }
    };
};