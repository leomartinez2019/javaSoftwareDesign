
/**
 * PhraseFilter returns quakes with sought words in info
 */

public class PhraseFilter implements Filter
{
    // "where" can be "start", "any", or "end"
    private String where;
    private String phrase;
    
    public PhraseFilter(String position, String keyword) {
      where = position;
      phrase = keyword;
    } 

    public boolean satisfies(QuakeEntry qe) {
      if (where.equals("start"))
        return qe.getInfo().startsWith(phrase);
      if (where.equals("end"))
        return qe.getInfo().endsWith(phrase);
      if (where.equals("any"))
        return qe.getInfo().indexOf(phrase) != -1;
      return false;
    }

}
