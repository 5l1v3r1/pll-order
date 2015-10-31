# pll-order

This figures out sequences of [PLLs](https://www.speedsolving.com/wiki/index.php/PLL) which use each PLL exactly once and arrive at the solved state. In addition, it approximates the probability that a sequence chosen at random will do so.

# Running

    $ lein run

# Results

Here is one identity PLL sequence (note that the PLLs must be facing as they are on Speedsolving's PLL page):

    (Ja T Na Aa Gc Gd Nb Ga E Y Jb Ab Z Gb Ua V H Rb Ra Ub F)

On average, I found that roughy 0.7% of sequences arrive at the identity. This is roughly 1/(4!*4!/4), or the joint probability of a given even edge permutation and corner permutation.