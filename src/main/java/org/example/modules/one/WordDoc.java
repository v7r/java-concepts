package org.example.modules.one;

// Following definition is valid

/*
public final class WordDoc extends Doc {
}
*/

// following definition is also valid
/*
public sealed class WordDoc extends Doc permits WordDoc2024 {
}
*/


// The following is a non-sealed abstract class. Any class can extend this class.
public non-sealed abstract class WordDoc extends Doc {
}
