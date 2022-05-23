import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {
    
    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("./test-file.md"));
        List<String> expect = List.of("b.com", "an-image.jpeg", "a-link.html");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    
    @Test
    public void testFile2() throws IOException {
        String contents= Files.readString(Path.of("./test-file2.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testMissingCloseParen() {
        String contents= "[link title](a.com";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testWhiteSpaceAroundLink() {
        String contents= "[link title](   a.com   )";
        List<String> expect = List.of("a.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void vimTest() {
        String contents= "[sample text](an-interesting-link.org)";
        List<String> expect = List.of("an-interesting-link.org");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    
    // @Test
    // public void testSnippet1() throws IOException {
    //     String contents= Files.readString(Path.of("./snippet-1.md"));
    //     List<String> expect = List.of("google.com");
    //     assertEquals(expect, MarkdownParse.getLinks(contents));
    // }

    // @Test
    // public void testSnippet2() throws IOException {
    //     String contents= Files.readString(Path.of("./snippet-2.md"));
    //     List<String> expect = List.of("a.com", "a.com", "example.com");
    //     assertEquals(expect, MarkdownParse.getLinks(contents));
    // }

    // @Test
    // public void testSnippet3() throws IOException {
    //     String contents= Files.readString(Path.of("./snippet-3.md"));
    //     List<String> expect = List.of(
    //         "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
    //     assertEquals(expect, MarkdownParse.getLinks(contents));
    // }

    // @Test
    // public void testNestedParens() throws IOException {
    //     String contents = Files.readString(Path.of("test-parens-inside-link.md"));
    //     List<String> expect = List.of("something.com()", "something.com((()))", "something.com", "boring.com");
    //     assertEquals(expect, MarkdownParse.getLinks(contents));
    // }

}
