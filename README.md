Temporal Random Indexing (v. 0.20 - 15/03/2015)
===============================================

General info
------------

This software provides a framework for the Temporal Random Indexing (TRI) technique. TRI is able to build WordSpaces taking into account temporal information. A WordSpace is a geometrical space in which words are represented as mathematical points. Similar words are represented close in the WordSpace. TRI can build different WordSpaces for several time periods allowing the analysis of how words change their meaning over time.

The WordSpaces are built using a corpus of documents annotated with the year of publication.

Details about the algorithm are published in the following paper:

@inproceedings{clic2014a,<br>
	title            = "Analysing Word Meaning over Time by Exploiting Temporal Random Indexing",<br>
	year             = "2014",<br>
	author           = "Pierpaolo Basile and Annalina Caputo and Giovanni Semeraro",<br>
	booktitle        = "First Italian Conference on Computational Linguistics CLiC-it 2014",<br>
	editor           = "Roberto Basili and Alessandro Lenci and Bernardo Magnini",<br>
	publisher        = "Pisa University Press",<br>
	url              = "http://clic.humnet.unipi.it/proceedings/Proceedings-CLICit-2014.pdf"}<br>

Please, cite the paper if you adopt our tool.

Usage
-----

The TRI framework provides both tools for building WordSpaces and a shell to query WordSpaces performing linguistic analysis.

Prepare the corpus:

1.	Create a directory for the corpus
2.	Copy all documents in the directory, each document must contain the information about the publication year in this format: filename\_year, for example *myfile\_1981*
3.	Run the class **di.uniba.it.tri.occ.BuildOccurrence** with the parameters: *corpusDir* *outputOccDir* *windowSize* *extractor\_class* *regular expression used to filter filenames*. The *corpusDir* is the corpus directory (step 1), *outputOccDir* is the directory in which information about co-occurrences will be stored. The *extractor\_class* is the name of the class used to extract text from files. This class must implement the interface: *di.uniba.it.tri.extractor.Extractor*. Three extractors are implemented: *GutenbergExtractor* for the Gutenberg Project; *AANExtractor* for the AAN corpus; and *TxtExtractor* for plain text files.

Build the WordSpaces:
1.	Run the class **di.uniba.it.tri.space.SpaceBuilder** with the arguments: *outputOccDir* *outputSpaceDir* *dimension* *seed* *dictionarySize*. The *outputOccDir* is the directory of co-occurrences, *outputSpaceDir* is the directory in which WordSpaces will be stored, *dimension* and *seed* are Random Indexing parameters (1000 and 20 are good values), and the dictionarySize is the number of terms considered into the vocabulary (the most frequent terms are considered).


After these steps the *outputSpaceDir* contains a WordSpace for each year in the corpus. Now you can use the TRI shell to analyze the corpus running the class: **di.uniba.it.tri.shell.TriShell**. If you note some problems related to characters encoding you can run the shell passing as argument the charset. The default charset is ISO-8859-1. Type *help* for shell usage, _help \*_ to show the commands list and *help command* to visualize info about a specific command.

Command Line Guideline
----------------------

**di.uniba.it.tri.shell.TriShell**

usage: Run the TRI shell<br>
 -c <arg>   The charset used by the shell (optional)

**di.uniba.it.tri.occ.BuildOccurrence**

usage: Build the co-occurrences matrices given the set of files with the year of publication [-c <arg>] [-e <arg>] [-o <arg>] [-r <arg>] [-w <arg>]<br>
 -c <arg>   The corpus directory containing files with the year of publication<br>
 -e <arg>   The class used to extract the content from files<br>
 -o <arg>   Output directory where the output will be stored<br>
 -r <arg>   Regular expression used to fetch files (optional, default ".+")<br>
 -w <arg>   The window size used to compute the co-occurrences (optional, default 5)

**di.uniba.it.tri.space.SpaceBuilder**

usage: Build WordSpace using Temporal Random Indexing [-c <arg>] [-d
       <arg>] [-o <arg>] [-s <arg>] [-v <arg>]<br>
 -c <arg>   The directory containing the co-occurrences matrices<br>
 -d <arg>   The vector dimension (optional, defaults 300)<br>
 -o <arg>   Output directory where WordSpaces will be stored<br>
 -s <arg>   The number of seeds (optional, defaults 10)<br>
 -v <arg>   The dictionary size (optional, defaults 100000)

Shell Guideline
---------------

**set** *main_dir*<br>
set the main directory in which Temporal Random Indexing spaces are stored, this is the output of the **SpaceBuilder**

**year** (*start* *end*)\*<br>
list the available years in the *main_dir*, it is possible to set time ranges (*start* *end*)\*

**load** *file|mem* (*name* *year*)\*<br>
load one or more vector readers of the specified type (*mem* or *file*) and years. If both name and years are not provided the elemental vector reader is loaded

**fload** *file|mem* *name* *filename*<br>
load a vector reader called *name* of the specified type (*mem* or *file*) from a file (*filename*)

**get** *vector_reader_name* *vector_name* *word*<br>
get the *word* vector from the *vector_reader* and store it in the memory using the *vector_name*

**addv** *vector_reader_name* *vector_name* *vector*+<br>
get and sum multiple *vectors* in memory and store the result in the memory using the *vector_name*

**add** *vector_reader_name* *vector_name* *word*+<br>
get and sum multiple *word* vectors from the *vector_reader* and store the result in the memory using the *vector_name*

**tri** *vector_reader_name* *start_year* *end_year*<br>
create a new temporal space named *vector_reader_name* combining spaces from the *start_year* to the *end_year*

**ftri** *output_filename* *start_year* *end_year*<br>
create a new temporal space combining spaces from the *start_year* to the *end_year* and save the result on disk

**sim**	*vector_name1* *vector_name2*<br>
compute cosine similarity between two vectors

**sims** *number_of_results* *vector_reader_name1* *vector_reader_name2*<br>
find words that change meaning between two WordSpaces

**near** *number_of_results* *vector_reader_name* *vector_name*<br>
print nearest vectors given the vector reader (*vector_reader_name*) and the word vector (*vector_name*)

**compare** *number_of_results* *vector_reader_name1* *vector_reader_name2* *vector_name1* *vector_name2*<br>
compare nearest vectors of the *vector_name1* in the *vector_reader_name1* and the *vector_name2* in the *vector_reader_name2*, this command is used to compare nearest vectors in two different word spaces

**indexelem**<br>
create the words index of the elemental vector space

**index** *file|mem* *name*<br>
create a words index from a vector reader (*name*) using a filename (*name*) or a previous reader loaded in memory (mem)

**search** *number_of_resutls* *query*<br>
search in the current words index

**count** *vector_reader_name*<br>
return the number of vectors in the vector reader

**list** *stores|vectors|sets*<br>
list vector readers (*stores*) or *vectors* or *sets* stored in the memory

**clear**	*stores|vectors|index* *name*\*<br>
remove one or more vector readers (*stores*) or vectors (*vectors*) called *name* or the words index. If *name* is not provided all the elements are removed

**cset**	*name*<br>
create a new set of words called *name*

**aset** *name* *word*+<br>
add words to a set called *name*

**pset** *name*<br>
print the set called *name*

**dset** *name*<br>
delete a set called *name*

**rset** *name* *word*+<br>
remove words from a set called *name*

**vset** *vector_reader_name* *set_name* *vector_name*<br>
convert a set of words called *set_name* into a vector (*vector_name*) fetching vectors from the vector reader (*vector_reader_name*)

**sset** *name* *number_of_results* *query*<br>
search in the words index and save results in a set called *name*

Contacts
--------
Pierpaolo Basile, pierpaolo.basile@gmail.com.
